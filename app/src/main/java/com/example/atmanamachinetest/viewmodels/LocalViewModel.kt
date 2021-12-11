package com.example.atmanamachinetest.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.atmanamachinetest.model.room.UserDAO
import com.example.atmanamachinetest.model.room.UserDataTable
import com.example.atmanamachinetest.model.room.UserDatabase
import com.example.atmanamachinetest.model.room.UserRepository
import kotlinx.coroutines.launch

class LocalViewModel(application: Application) : ViewModel() {

    private val dao : UserDAO = UserDatabase.getRoomInstance(application).userDao()
    private val repository : UserRepository =  UserRepository(dao)

    private lateinit var userData : LiveData<List<UserDataTable>>



    init {
        loadLocalStorage()
    }

    private fun loadLocalStorage() {
        viewModelScope.launch {
            userData = repository.getData.asLiveData()
        }
    }

    fun insertData(userData: UserDataTable){
        viewModelScope.launch {
            repository.insertData(userData)
        }
    }

    fun updateData(userData: UserDataTable) {
        viewModelScope.launch {
            repository.updateData(userData)
        }
    }

    fun deleteData(userData: UserDataTable) {
        viewModelScope.launch {
            repository.deleteData(userData)
        }
    }

    fun getLiveUserData() : LiveData<List<UserDataTable>> = userData

}


class LocalViewModelFactory (var application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LocalViewModel::class.java))
            return LocalViewModel(application = application) as T
        throw IllegalArgumentException("Unknown Class")
    }

}