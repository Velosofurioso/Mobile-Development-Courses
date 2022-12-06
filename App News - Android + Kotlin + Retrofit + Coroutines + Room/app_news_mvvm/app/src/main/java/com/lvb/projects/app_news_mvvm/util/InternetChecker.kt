package com.lvb.projects.app_news_mvvm.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class InternetChecker {

    companion object {
        fun checkForInternetConnection(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                else -> false
            }
            return connectivityManager.activeNetworkInfo?.isAvailable ?: false
        }
    }
}