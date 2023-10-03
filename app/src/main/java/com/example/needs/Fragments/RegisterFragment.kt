package com.example.needs.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.needs.R
import com.example.needs.Repositories.AuthenticationRepository
import com.example.needs.ViewModels.AuthenticationViewModel
import com.example.needs.ViewModels.AuthenticationViewModelFactory
import com.example.needs.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

   private lateinit var viewModel: AuthenticationViewModel
    private lateinit var binding : FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        val repositiory = AuthenticationRepository()
        viewModel = ViewModelProvider(this,AuthenticationViewModelFactory(repositiory))
            .get(AuthenticationViewModel::class.java)

        binding.signupBtn.setOnClickListener {
            val encodedEmail = binding.editTextEmailAddress.text.toString()
            val email = encodedEmail.replace(".", "_").replace("@", ",")
            val pass = binding.PasswordEditText.text.toString()
            val retypepass = binding.retypepasswordEditText.text.toString()

            if(!email.isEmpty() && !pass.isEmpty()
                &&
                !retypepass.isEmpty()
                ){

                if(pass == retypepass){

                   viewModel.signup(email,pass){
                       success,message ->
                       if(success) {

                           Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
                       }
                       else{

                           Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()

                       }
                   }

                }

                else{
                    Toast.makeText(requireContext(),"The password and retype password are not same....",Toast.LENGTH_SHORT).show()

                }




            }
            else{
                Toast.makeText(requireContext(),"Please, insert all the fields",Toast.LENGTH_SHORT).show()

            }
        }





        return binding.root
    }


}