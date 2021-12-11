package com.example.atmanamachinetest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.atmanamachinetest.databinding.FragmentLocalBinding
import android.graphics.drawable.ColorDrawable
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.atmanamachinetest.R
import com.example.atmanamachinetest.adapters.LocalDataAdapter
import com.example.atmanamachinetest.databinding.FragmentAddDataBinding
import com.example.atmanamachinetest.interfaces.OnViewClickListener
import com.example.atmanamachinetest.model.room.UserDataTable
import com.example.atmanamachinetest.viewmodels.LocalViewModel
import com.example.atmanamachinetest.viewmodels.LocalViewModelFactory

class LocalFragment : Fragment(), OnViewClickListener{

    private lateinit var binding : FragmentLocalBinding
    private lateinit var dialog : AlertDialog
    private lateinit var localViewModel: LocalViewModel

    private lateinit var adapter : LocalDataAdapter

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_local, container, false)

        val factory = LocalViewModelFactory(requireActivity().application)

        localViewModel = ViewModelProvider(this,factory)[LocalViewModel::class.java]

        binding.idFabAdd.setOnClickListener {
            openCustomDialog(null)
        }

        localViewModel.getLiveUserData().observe(viewLifecycleOwner,{ userData ->

            if(userData.isEmpty()){
                binding.idLocalRecyclerView.visibility = View.GONE
                binding.idLottieFiles.visibility = View.VISIBLE
            }else{
                binding.idLocalRecyclerView.visibility = View.VISIBLE
                binding.idLottieFiles.visibility = View.GONE
                adapter = LocalDataAdapter(userData,requireContext())
                binding.idLocalRecyclerView.adapter = adapter
                adapter.setListener(object : OnViewClickListener{
                    override fun onEditClick(view: View, userData: UserDataTable) {
                        openCustomDialog(userData)
                    }

                    override fun onDeleteClick(view: View, userData: UserDataTable) {
                        localViewModel.deleteData(userData)
                    }
                })
            }




        })



        return binding.root
    }

    private fun openCustomDialog(userData: UserDataTable?) {

        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogBinding = DataBindingUtil.inflate<FragmentAddDataBinding>(inflater,R.layout.fragment_add_data,null,true)


        if(userData != null){
            //set data for edit
            dialogBinding.idInputFirstname.setText(userData.firstName)
            dialogBinding.idInputLastname.setText(userData.lastName)
            dialogBinding.idInputEmail.setText(userData.email)
        }



        dialogBinding.idImgClose.setOnClickListener { dialog.dismiss() }

        dialogBinding.idButtonSubmit.setOnClickListener {
            firstName = dialogBinding.idInputFirstname.text.toString().trim()
            lastName = dialogBinding.idInputLastname.text.toString().trim()
            email = dialogBinding.idInputEmail.text.toString().trim()

            if(firstName.isEmpty()){
                dialogBinding.idInputFirstname.error = "Please Enter First Name"
                dialogBinding.idInputFirstname.requestFocus()
                return@setOnClickListener
            }

            if(lastName.isEmpty()){
                dialogBinding.idInputLastname.error = "Please Enter Last Name"
                dialogBinding.idInputLastname.requestFocus()
                return@setOnClickListener
            }

            if(email.isEmpty() || !email.endsWith("@gmail.com")){
                dialogBinding.idInputEmail.error = "Invalid Email ID Entered"
                dialogBinding.idInputEmail.requestFocus()
                return@setOnClickListener
            }



            if(userData == null){
                localViewModel.insertData(UserDataTable(0,firstName, lastName, email))
            }else{
                localViewModel.updateData(UserDataTable(userData.id,firstName, lastName, email))
            }

            dialog.dismiss()
        }



        builder.setView(dialogBinding.root)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun onEditClick(view: View, userData: UserDataTable) {
        localViewModel.updateData(userData)
    }

    override fun onDeleteClick(view: View, userData: UserDataTable) {
        localViewModel.deleteData(userData)
    }
}