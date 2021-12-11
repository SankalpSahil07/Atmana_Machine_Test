package com.example.atmanamachinetest.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserDataTable::class],version = 1,exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDAO

    companion object{
        private var INSTANCE : UserDatabase? = null

        fun getRoomInstance(context: Context) : UserDatabase{

            synchronized(this){

                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        UserDatabase::class.java,
                        "UserDB").build()
                    return INSTANCE as UserDatabase
                }
                return INSTANCE as UserDatabase
            }
        }
    }
}