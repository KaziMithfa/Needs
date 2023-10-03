package com.example.needs.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.needs.R
import com.example.needs.Repositories.AuthenticationRepository
import com.example.needs.ViewModels.AuthenticationViewModel
import com.example.needs.ViewModels.AuthenticationViewModelFactory
import com.example.needs.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthenticationViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLoginBinding.inflate(layoutInflater, container,false)

        val repositiory = AuthenticationRepository()
        viewModel = ViewModelProvider(this, AuthenticationViewModelFactory(repositiory))
            .get(AuthenticationViewModel::class.java)

        binding.loginBtnId.setOnClickListener {

            var email = binding.loginemail.text.toString()
            var pass = binding.loginpassword.text.toString()
            if(email.isEmpty()){
                Toast.makeText(requireContext(),"Please, insert the email",Toast.LENGTH_SHORT).show()
            }
            else if(pass.isEmpty()){
                    Toast.makeText(requireContext(),"Please, insert the Password",Toast.LENGTH_SHORT).show()

            }

            else{
                val encodedEmail = email.replace(".", "_").replace("@", ",")

                viewModel.signIn(encodedEmail,pass){
                    success,message ->
                    if(success){
                        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()

                    }


                }
            }
        }


        return binding.root


    }


}