package com.example.smartdoctor.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import javax.inject.Inject

class CheckConnection @Inject constructor(private val cm: ConnectivityManager) : LiveData<Boolean>() {

    private val networkCallBack = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val request = NetworkRequest.Builder()
        cm.registerNetworkCallback(request.build(), networkCallBack)

    }

    override fun onInactive() {
        super.onInactive()
        cm.unregisterNetworkCallback(networkCallBack)
    }
}