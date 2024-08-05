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

class LoginPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        auth = FirebaseAuth.getInstance()

        auth = Firebase.auth


        val SignIn: Button = findViewById(R.id.btnSignIn)
        SignIn.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
            performSignIn()
        }

        val signUp: TextView = findViewById(R.id.btnSignUp)
        signUp.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)

        }
    }

    private fun performSignIn() {
        val mail = findViewById<EditText>(R.id.etmailID)
        val password: EditText = findViewById(R.id.etPassword)
        val SignIn: Button = findViewById(R.id.btnSignIn)
        val SignUp: Button = findViewById(R.id.btnSignUp)
        val inputEmail: String
        inputEmail = mail.text.toString()
        val inputPassword: String
        inputPassword = password.text.toString()

        if (mail.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()

        } else {

            auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        val SignIn: Button = findViewById(R.id.btnSignIn)
                        SignIn.setOnClickListener {
                            val intent = Intent(this, MainPage::class.java)
                            startActivity(intent)
                            Toast.makeText(baseContext, "Signed in succesfully", Toast.LENGTH_LONG)
                                .show()
                        }

                    } else {
                        Toast.makeText(
                            baseContext, "Authentication failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this, "Error occurred ${it.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        auth = FirebaseAuth.getInstance()
//
//
//        val signIn: Button = findViewById(R.id.btnSignIn)
//        signIn.setOnClickListener {
//            if (checking()) {
//                var mail = findViewById<EditText>(R.id.etmailID)
//                var password = findViewById<EditText>(R.id.etPassword)
//                var inputEmail = mail.text.toString()
//                var inputPassword = password.text.toString()
//                auth.signInWithEmailAndPassword(inputEmail, inputPassword)
//                    .addOnCompleteListener(this) { task ->
//                        if (task.isSuccessful) {
//                            val signIn: TextView = findViewById(R.id.btnSignIn)
//                            signIn.setOnClickListener {
//                                val intent = Intent(this, RegisterPage::class.java)
//                                startActivity(intent)
//                                Toast.makeText(
//                                    baseContext, "Signed in succesfully",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//
//                        }
//                        else{
//                            Toast.makeText(baseContext,"Login failed",
//                                Toast.LENGTH_SHORT).show()
//                    }
//
//
//                }
//            }
//        }
//    }
//
//        private fun checking(): Boolean{
//        var mail = findViewById<EditText>(R.id.etmailID)
//        var password = findViewById<EditText>(R.id.etPassword)
//
//        var inputEmail = mail.text.toString()
//        var inputPassword = password.text.toString()
//        if(mail.text.toString().trim{it<=' '}.isNotEmpty() && password.text.toString().trim{it<=' '}.isNotEmpty()){
//            return true
//        }
//        return false
//    }
//
//}
//
//
