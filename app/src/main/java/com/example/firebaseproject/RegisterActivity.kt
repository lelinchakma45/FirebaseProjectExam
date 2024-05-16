package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseproject.ViewModel.AuthViewModel
import com.example.firebaseproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.signupBtn.setOnClickListener{
            val user_email = binding.userEmailInput.text.toString()
            val password = binding.pass.text.toString()
            val con_pass = binding.cpass.text.toString()
            if (user_email.isNullOrEmpty()||password.isNullOrEmpty()||con_pass.isNullOrEmpty()){
                Toast.makeText(this,"Please all field is fill required",Toast.LENGTH_SHORT).show()
            }
            else{
                if(password != con_pass){
                    Toast.makeText(this,"Password Don't Match",Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModel.signup(email = user_email, password = con_pass).observe(this) {
                        Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
                        if (it.equals("Sign Up Success")) {
                            startActivity(Intent(this, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this, "Sign up Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        binding.loginTextBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}