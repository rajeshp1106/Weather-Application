package com.raya.agriapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        auth = Firebase.auth
        val LogIn: TextView = findViewById(R.id.btnLogIn)
        LogIn.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }
        val registerButton: Button =findViewById(R.id.btnRegister)
        registerButton.setOnClickListener{
            performSignUp()
        }
    }
    private fun performSignUp(){
        val username = findViewById<EditText>(R.id.etUserName)
        val mail = findViewById<EditText>(R.id.etmailID)
        val password = findViewById<EditText>(R.id.etPassword)
        val MobileNumber = findViewById<EditText>(R.id.etPhone)
        val ConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        if(mail.text.isEmpty()||password.text.isEmpty()||username.text.isEmpty()||MobileNumber.text.isEmpty()||ConfirmPassword.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_SHORT).show()
        }

        val inputEmail = mail.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val registerButton: TextView = findViewById(R.id.btnRegister)
                    registerButton.setOnClickListener {
                        val intent = Intent(this, LoginPage::class.java)
                        startActivity(intent)
                        Toast.makeText(baseContext,"Succesfully registered",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(baseContext,"Authentication failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,"Error occurred ${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            }
    }
}