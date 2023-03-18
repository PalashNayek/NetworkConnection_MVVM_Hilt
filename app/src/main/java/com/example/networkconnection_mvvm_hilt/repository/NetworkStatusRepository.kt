package com.example.networkconnection_mvvm_hilt.repository

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class NetworkStatusRepository @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean>
        get() = _isConnected

    fun startNetworkCallback() {
        val builder = NetworkRequest.Builder()

        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    _isConnected.postValue(true)
                }

                override fun onLost(network: Network) {
                    _isConnected.postValue(false)
                }
            }
        )
    }

}
