package com.example.atmanamachinetest.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atmanamachinetest.databinding.RawLayoutLocalBinding
import com.example.atmanamachinetest.interfaces.OnViewClickListener
import com.example.atmanamachinetest.model.room.UserDataTable

class LocalDataAdapter(private var list : List<UserDataTable>, context: Context) : RecyclerView.Adapter<LocalDataAdapter.ViewHolder>() {

    private lateinit var onClickListener : OnViewClickListener


    fun setListener(listener: OnViewClickListener){
        onClickListener = listener
    }


    class ViewHolder(var binding: RawLayoutLocalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RawLayoutLocalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.idTextFirstLastName.text = list[position].firstName + list[position].lastName
        holder.binding.idTextEmail.text = list[position].email
        holder.binding.idTextId.text = list[position].id.toString()

        holder.binding.idImgEdit.setOnClickListener { onClickListener.onEditClick(holder.binding.idImgEdit,list[position]) }
        holder.binding.idImgDelete.setOnClickListener { onClickListener.onDeleteClick(holder.binding.idImgDelete,list[position]) }
    }

    override fun getItemCount(): Int = list.size

}