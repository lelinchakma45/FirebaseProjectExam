package com.example.firebaseproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel:ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    fun signup(email:String,password:String):LiveData<String>{
        val result = MutableLiveData<String>()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                result.value = "Sign Up Success"
            }
            else{
                result.value = "Sign Up Failed"
            }
        }
        return result
    }

    fun signin(email:String,password:String):LiveData<String>{
        val result = MutableLiveData<String>()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                result.value = "Sign Up Success"
            }
            else{
                result.value = "Sign Up Failed"
            }
        }
        return result
    }
    fun logout(){
        auth.signOut()
    }
}