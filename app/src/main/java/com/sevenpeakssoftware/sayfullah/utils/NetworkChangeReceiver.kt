package com.sevenpeakssoftware.sayfullah.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.sayfullah.utils.tools.NetworkUtil

class NetworkChangeReceiver : BroadcastReceiver() {

    val networkStatusLv = MutableLiveData<Int>()


    override fun onReceive(context: Context?, intent: Intent) {
        val status = NetworkUtil.getConnectivityStatusString(context!!)
        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.getAction()) {
            networkStatusLv.postValue(status)
        }
    }
}