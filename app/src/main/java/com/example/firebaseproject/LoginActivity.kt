package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseproject.ViewModel.AuthViewModel
import com.example.firebaseproject.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.signupBtn.setOnClickListener{
            val user_email = binding.userEmailInput.text.toString()
            val password = binding.pass.text.toString()
            if (user_email.isNullOrEmpty()||password.isNullOrEmpty()){
                Toast.makeText(this,"Please all field is fill required", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.signin(email = user_email, password = password).observe(this) {
                    Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
        binding.registerTextBtn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser?.uid != null){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}