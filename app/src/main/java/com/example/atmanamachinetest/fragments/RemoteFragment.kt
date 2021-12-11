package com.example.atmanamachinetest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atmanamachinetest.R
import com.example.atmanamachinetest.adapters.RemoteDataAdapter
import com.example.atmanamachinetest.databinding.FragmentRemoteBinding
import com.example.atmanamachinetest.model.data.PersonData
import com.example.atmanamachinetest.viewmodels.RemoteViewModel

class RemoteFragment : Fragment() {

    private lateinit var binding: FragmentRemoteBinding
    private lateinit var remoteViewModel : RemoteViewModel
    private var reqResAllList = arrayListOf<PersonData>()

    private var isScrolling = false
    private var currentItems : Int = 0
    private var totalItems : Int = 0
    private var scrollOutItems : Int = 0

    private lateinit var manager: LinearLayoutManager
    private var page = 1




    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_remote, container, false)
        remoteViewModel = ViewModelProvider(this)[RemoteViewModel::class.java]

        manager = LinearLayoutManager(requireContext())

        remoteViewModel.reqResData.observe(viewLifecycleOwner, { reqResList ->

            reqResAllList.addAll(reqResList.data)

            binding.idProgressBar.visibility = View.GONE

            if(reqResList != null){
                binding.idLottieFiles.visibility = View.GONE
                binding.idReqResRecyclerView.visibility = View.VISIBLE
                binding.idReqResRecyclerView.layoutManager = manager
                binding.idReqResRecyclerView.adapter = RemoteDataAdapter(reqResAllList)
                binding.idReqResRecyclerView.adapter?.notifyDataSetChanged()

            }else{
                //show empty lottie file
                binding.idReqResRecyclerView.visibility = View.GONE
                binding.idLottieFiles.visibility = View.VISIBLE
            }
        })



        binding.idReqResRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrollOutItems = manager.findFirstVisibleItemPosition()

                if(isScrolling && (currentItems + scrollOutItems == totalItems)){
                    isScrolling = false
                    if(reqResAllList.size == 12){
                        Toast.makeText(requireContext(), "Data Exhausted", Toast.LENGTH_SHORT).show()
                        return
                    }else{
                        remoteViewModel.loadRequestResponseData(++page)
                    }

                }
            }
        })




        return binding.root

    }
}