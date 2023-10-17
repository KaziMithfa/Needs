package com.example.needs.Fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.needs.R
import com.example.needs.Repositories.AuthenticationRepository
import com.example.needs.ViewModels.AuthenticationViewModel
import com.example.needs.ViewModels.AuthenticationViewModelFactory
import com.example.needs.databinding.FragmentWelcomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task


class WelcomeFragment : Fragment() {



    private lateinit var binding : FragmentWelcomeBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var viewModel: AuthenticationViewModel


    companion object{
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentWelcomeBinding.inflate(layoutInflater,container,false)

        val repositiory = AuthenticationRepository()
        viewModel = ViewModelProvider(this, AuthenticationViewModelFactory(repositiory))
            .get(AuthenticationViewModel::class.java)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
        requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(),gso)

        binding.loginwithgoogletxt.setOnClickListener{
            singInwithGoogle()
        }


        binding.registerTxt.setOnClickListener(){
            findNavController().navigate(R.id.action_welcomeFragment2_to_registerFragment2)
        }

        binding.logintxtId.setOnClickListener(){
            findNavController().navigate(R.id.action_welcomeFragment2_to_loginFragment)
        }


        return binding.root

        //return inflater.inflate(R.layout.fragment_welcome, container, false)
    }


    private fun singInwithGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->

       if(result.resultCode == Activity.RESULT_OK){
           val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
           handleResult(task)
       }
    }

    private fun handleResult(task : Task<GoogleSignInAccount>){
        if(task.isSuccessful){
            val account : GoogleSignInAccount? = task.result

            if(account != null){
                viewModel.signInwithGoogleAccount(account){
                    success ->

                    if(success){

                        Toast.makeText(requireContext(),"You have been logged in successfully...",Toast.LENGTH_SHORT).show()
                    }

                    else{
                        Toast.makeText(requireContext(),"You have not  logged in successfully...",Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }
    }


}