package com.example.atmanamachinetest.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserLocalData")
class UserDataTable (

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int,

        @ColumnInfo(name = "firstName")
        var firstName: String,

        @ColumnInfo(name = "lastName")
        var lastName: String,

        @ColumnInfo(name = "email")
        var email : String)