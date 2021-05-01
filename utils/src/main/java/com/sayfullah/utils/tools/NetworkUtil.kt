package com.sayfullah.utils.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log

class NetworkUtil {
    companion object{
        const val TYPE_WIFI = 1
        const val TYPE_MOBILE = 2
        const val TYPE_ETHERNET = 3
        const val TYPE_NOT_CONNECTED = 0
        const val NETWORK_STATUS_NOT_CONNECTED = 0
        const val NETWORK_STATUS_WIFI = 1
        const val NETWORK_STATUS_MOBILE = 2
        const val NETWORK_STATUS_ETHERNET = 3

        fun getConnectivityStatus(context: Context): Int {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return TYPE_MOBILE
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return TYPE_WIFI
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return TYPE_ETHERNET
                    }
                }
                return TYPE_NOT_CONNECTED
            }else{
                val cm: ConnectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo = cm.getActiveNetworkInfo()!!
                if (null != activeNetwork) {
                    if (activeNetwork.getType() === ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
                    if (activeNetwork.getType() === ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
                }
                return TYPE_NOT_CONNECTED
            }
        }

        fun getConnectivityStatusString(context: Context): Int {
            val conn = getConnectivityStatus(context)
            var status = 0
            if (conn == TYPE_WIFI) {
                status = NETWORK_STATUS_WIFI
            } else if (conn == TYPE_MOBILE) {
                status = NETWORK_STATUS_MOBILE
            } else if (conn == TYPE_NOT_CONNECTED) {
                status = NETWORK_STATUS_NOT_CONNECTED
            }
            return status
        }
    }
}