package com.example.needs.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.needs.R
import com.example.needs.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {



    private lateinit var binding : FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentWelcomeBinding.inflate(layoutInflater,container,false)

        binding.registerTxt.setOnClickListener(){
            findNavController().navigate(R.id.action_welcomeFragment2_to_registerFragment2)
        }

        binding.logintxtId.setOnClickListener(){
            findNavController().navigate(R.id.action_welcomeFragment2_to_loginFragment)
        }


        return binding.root

        //return inflater.inflate(R.layout.fragment_welcome, container, false)
    }


}