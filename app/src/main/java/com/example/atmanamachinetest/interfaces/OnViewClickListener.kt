package com.example.atmanamachinetest.interfaces

import android.view.View
import com.example.atmanamachinetest.model.room.UserDataTable

interface OnViewClickListener {

    fun onEditClick(view: View, userData: UserDataTable)

    fun onDeleteClick(view: View, userData: UserDataTable)
}
