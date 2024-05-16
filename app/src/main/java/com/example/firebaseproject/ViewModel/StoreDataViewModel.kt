package com.example.firebaseproject.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StoreDataViewModel:ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("news_data")

    fun writeData(data: String,age:Int) {
        val userId = myRef.push().key // Generate a unique key for each user
        userId?.let {
            myRef.child(it).child("name").setValue(data)
            myRef.child(it).child("age").setValue(age)
        }
    }

    fun readData(listener: ValueEventListener) {
        myRef.addValueEventListener(listener)
    }

    override fun onCleared() {
        super.onCleared()
        // Clean up listeners or resources here if needed
    }
}