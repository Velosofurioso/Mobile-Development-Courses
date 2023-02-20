import 'package:app_store_manager/blocs/login_bloc.dart';
import 'package:app_store_manager/screens/home_screen.dart';
import 'package:app_store_manager/widgets/input_field.dart';
import 'package:flutter/material.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {

  final _loginBloc = LoginBloc();


  @override
  void initState() {
    super.initState();

    _loginBloc.outState.listen((state) {
      switch(state) {
        case LoginState.SUCCESS:
          Navigator.of(context).pushReplacement(
              MaterialPageRoute(builder: (context) => const HomeScreen())
          );
          break;

        case LoginState.FAIL:
          showDialog(context: context, builder: (context) => const AlertDialog (
            title: Text("Erro"),
            content: Text("Você não possui os privilégios necessários")
          ));
          break;

        case LoginState.LOADING:
        case LoginState.IDLE:
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[850],
      body: StreamBuilder<LoginState>(
        stream: _loginBloc.outState,
        builder: (context, snapshot) {
          switch(snapshot.data) {
            case LoginState.LOADING:
              return const CircularProgressIndicator(valueColor:  AlwaysStoppedAnimation(Colors.pinkAccent));

            case LoginState.FAIL:
            case LoginState.SUCCESS:
            case LoginState.IDLE:
            case null:
             return Stack(alignment: Alignment.center, children: [
              Container(),
              SingleChildScrollView(
                child: Container(
                  margin: const EdgeInsets.all(16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      const Icon(
                        Icons.store_mall_directory,
                        color: Colors.pinkAccent,
                        size: 160,
                      ),
                      InputField(
                        icon: Icons.person_outline,
                        hint: "Usuário",
                        obscure: false,
                        stream: _loginBloc.outEmail,
                        onChanged: _loginBloc.changeEmail,
                      ),

                      InputField(
                          icon: Icons.lock_outline,
                          hint: "Senha",
                          obscure: true,
                          stream: _loginBloc.outPassword,
                          onChanged: _loginBloc.changePassword
                      ),
                      const SizedBox(height: 32),
                      StreamBuilder<bool>(
                          stream: _loginBloc.outSubmitValid,
                          builder: (context, snapshot) {
                            return SizedBox(
                              height: 50,
                              child: ElevatedButton(
                                style: ButtonStyle(
                                    textStyle: MaterialStateProperty.all(const TextStyle(color: Colors.white)),
                                    backgroundColor: MaterialStateProperty.resolveWith<Color?>((Set<MaterialState> states)
                                    {
                                      if (states.contains(MaterialState.disabled)) { return Colors.pinkAccent.withAlpha(140); }
                                      else { return Colors.pinkAccent; }
                                    })
                                ), // Change text Color
                                onPressed: snapshot.hasData ? _loginBloc.submit : null,
                                child: const Text("Entrar"),
                              ),
                            );
                          }
                      )
                    ],
                  ),
                ),
              ),
            ]);
          }

        }
      ),
    );
  }

  @override
  void dispose() {
    _loginBloc.dispose();
    super.dispose();
  }
}
