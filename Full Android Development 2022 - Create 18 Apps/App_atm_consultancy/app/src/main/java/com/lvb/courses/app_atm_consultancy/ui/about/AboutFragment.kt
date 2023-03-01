package com.lvb.courses.app_atm_consultancy.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lvb.courses.app_atm_consultancy.R
import com.lvb.courses.app_atm_consultancy.util.Constants
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val version = Element().setTitle("Version 1.0")

        return AboutPage(activity)
            .setImage(R.drawable.logo)
            .setDescription(context?.getString(R.string.company_description))


            .addGroup("Contact Me")
            .addEmail(Constants.APP_EMAIL, "Send a email")
            .addWebsite("https://www.google.com", "Acess our website")

            .addGroup("Social Media")
            .addInstagram("lucas_veloso20", "Instagram")
            .addGitHub("Velosofurioso", "GitHub")
            .addItem(version)
            .create()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}