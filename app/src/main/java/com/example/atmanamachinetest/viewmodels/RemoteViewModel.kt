package com.example.atmanamachinetest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atmanamachinetest.model.data.ReqResData
import com.example.atmanamachinetest.network_service.ApiService
import com.example.atmanamachinetest.network_service.NetworkStatus
import com.google.gson.Gson
import kotlinx.coroutines.launch

class RemoteViewModel : ViewModel() {

    private var _status = MutableLiveData<NetworkStatus>()
    val status: LiveData<NetworkStatus> = _status

    private var _reqResData = MutableLiveData<ReqResData>()
    val reqResData : LiveData<ReqResData> = _reqResData


    init {
        loadRequestResponseData(1)
    }

    fun loadRequestResponseData(pageNo : Int) {
        viewModelScope.launch {
            _status.value = NetworkStatus.LOADING
            try {
                _reqResData.value =  ApiService.api.getRequestResponseData(pageNo)
                _status.value = NetworkStatus.DONE
            }catch (ex : Exception){
                Log.e("AXE","Exception : $ex")
                _status.value = NetworkStatus.ERROR
                _reqResData.value = null
            }
        }
    }
}