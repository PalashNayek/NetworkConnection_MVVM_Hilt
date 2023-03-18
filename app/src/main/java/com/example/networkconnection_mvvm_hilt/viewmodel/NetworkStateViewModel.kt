package com.example.networkconnection_mvvm_hilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkconnection_mvvm_hilt.repository.NetworkStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkStateViewModel @Inject constructor(
    networkStatus: NetworkStatusRepository
) : ViewModel() {

    private val _networkLoss = MutableLiveData<Unit>()
    val networkLoss: LiveData<Unit>
        get() = _networkLoss

    private val _networkSuccess = MutableLiveData<Unit>()
    val networkSuccess: LiveData<Unit>
        get() = _networkSuccess

    init {
        networkStatus.isConnected.observeForever { isConnected ->
            if (!isConnected) {
                _networkLoss.postValue(Unit)
            } else {
                _networkSuccess.postValue(Unit)
            }
        }
        networkStatus.startNetworkCallback()
    }

}
