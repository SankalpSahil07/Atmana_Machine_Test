package com.example.atmanamachinetest.model.room

import kotlinx.coroutines.flow.Flow

class UserRepository(private var dao:UserDAO) {


    var getData : Flow<List<UserDataTable>> = dao.getAllUsersData()

    suspend fun insertData(usersData: UserDataTable){
        dao.insertData(usersData)
    }

    suspend fun updateData(usersData: UserDataTable){
        dao.updateData(usersData)
    }

    suspend fun deleteData(usersData: UserDataTable){
        dao.deleteData(usersData)
    }

}