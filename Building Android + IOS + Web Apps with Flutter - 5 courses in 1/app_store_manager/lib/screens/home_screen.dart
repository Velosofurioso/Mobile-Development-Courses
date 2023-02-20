import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {

  PageController? _pageController;
  int _page = 0;


  @override
  void initState() {
    super.initState();
    _pageController = PageController();
  }


  @override
  void dispose() {
    _pageController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[850],
      bottomNavigationBar: Theme(
        data: Theme.of(context).copyWith(
          canvasColor: Colors.pinkAccent,
          primaryColor: Colors.white,
          textTheme: Theme.of(context).textTheme.copyWith(
            caption: const TextStyle(color: Colors.white)
          )
        ),
        child: BottomNavigationBar(
          currentIndex: _page,
          onTap: (p) {
            _pageController?.animateToPage(
                p,
                duration: const Duration(milliseconds: 500),
                curve: Curves.ease
            );
          },
          items: const [
            BottomNavigationBarItem(
                icon: Icon(Icons.person),
                label: "Clientes"
            ),

            BottomNavigationBarItem(
                icon: Icon(Icons.shopping_cart),
                label: "Pedidos"
            ),

            BottomNavigationBarItem(
                icon: Icon(Icons.list),
                label: "Produtos"
            ),
          ],
        ),
      ),
      body: PageView (
        onPageChanged: (p) {
          setState(() {
            _page = p;
          });
        },
        controller: _pageController,
        children: [
          Container()
        ],
      ),
    );
  }
}
