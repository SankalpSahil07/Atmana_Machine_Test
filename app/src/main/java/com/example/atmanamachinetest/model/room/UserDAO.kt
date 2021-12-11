package com.example.atmanamachinetest.model.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(usersData: UserDataTable)

    @Query("SELECT * FROM UserLocalData ORDER BY id ASC")
    fun getAllUsersData() : Flow<List<UserDataTable>>

    @Update
    suspend fun updateData(usersData: UserDataTable)

    @Delete
    suspend fun deleteData(usersData: UserDataTable)
}