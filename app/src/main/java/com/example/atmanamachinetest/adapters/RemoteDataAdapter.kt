package com.example.atmanamachinetest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.atmanamachinetest.databinding.RawRemoteLayoutBinding
import com.example.atmanamachinetest.model.data.PersonData

class RemoteDataAdapter (var list: List<PersonData>) : RecyclerView.Adapter<RemoteDataAdapter.ViewHolder>(){

    class ViewHolder(var binding: RawRemoteLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RawRemoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.idImgAvtar.context).load(list[position].avatar).into(holder.binding.idImgAvtar)
        holder.binding.idTextFirstLastName.text = "${list[position].first_name} ${list[position].last_name}"
        holder.binding.idTextEmail.text = list[position].email
        holder.binding.idTextId.text = list[position].id.toString()
    }

    override fun getItemCount(): Int = list.size
}