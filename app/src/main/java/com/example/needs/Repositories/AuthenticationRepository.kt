package com.example.needs.Repositories

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.example.needs.Models.Users
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

public class AuthenticationRepository() {


    private val firebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance().getReference("Users")

    fun signUp(email : String ,pass : String,callback : (Boolean,String) -> Unit) {

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               if(!snapshot.hasChild(email)){

                   val userData = HashMap<String,Any>()
                   userData.put("email",email)
                   userData.put("password",pass)
                   databaseReference.child(email).updateChildren(userData).addOnCompleteListener{

                       if(it.isSuccessful){
                          callback(true,"The account has been created successfully.....")

                       }

                       else{
                           callback(false,it.exception?.message.toString())

                       }

                   }





               }

                else{

               }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    fun signIn(email: String,pass : String,callback: (Boolean, String) -> Unit){
        databaseReference.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.hasChild(email)){

                    val users = snapshot.child(email).getValue(Users::class.java)
                    if(users?.password == pass){
                        callback(true,"Logged in successfull")
                    }
                    else{
                        callback(false,"Password is incorrect")

                    }


                }
                else{
                    callback(false,"This email does not have an account...")
                }




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    fun signInWithGoogle(account : GoogleSignInAccount, onComplete : (Boolean) -> Unit){
        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                val user = firebaseAuth.currentUser

                if(user!= null){
                    val userEmail = user.email
                    val encodedEmail = userEmail?.replace(".", "_")?.replace("@", ",")
                    if (encodedEmail != null) {
                        databaseReference.child(encodedEmail).child("email").setValue(encodedEmail)
                            .addOnCompleteListener{
                                if(it.isSuccessful){
                                    onComplete(true)
                                }
                                else{
                                    onComplete(false)
                                }
                            }

                    }
                }
            }
        }

    }

}