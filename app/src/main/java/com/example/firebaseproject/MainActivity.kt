package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseproject.DataModel.StoreData
import com.example.firebaseproject.ViewModel.AuthViewModel
import com.example.firebaseproject.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.logoutBtn.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.seeDataBtn.setOnClickListener {
            startActivity(Intent(this,ShowDataActivity::class.java))
        }
        binding.saveDataBtn.setOnClickListener {
            val dataName = binding.name.text.toString().trim()
            val age = binding.age.text.toString().trim()

            if (dataName.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Initialize Firebase reference only once
            if (!::databaseReference.isInitialized) {
                databaseReference = FirebaseDatabase.getInstance().getReference("news_app")
            }

            val newsData = StoreData(dataName, age.toInt())

            // Push data to Firebase
            databaseReference.push().setValue(newsData).addOnSuccessListener {
                binding.name.text?.clear()
                binding.age.text?.clear()
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
