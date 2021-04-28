package com.eugenebaturov.letschat.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eugenebaturov.letschat.R
import com.eugenebaturov.letschat.ui.fragments.login.SignInFragment
import com.eugenebaturov.letschat.ui.fragments.login.SignUpFragment
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), SignInFragment.Callbacks, SignUpFragment.Callbacks {

    private lateinit var fragment: Fragment
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initFragment(SignInFragment())
        auth = FirebaseAuth.getInstance()
    }

    override fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
                return@addOnCompleteListener
        }
            .addOnFailureListener {task ->
                Toast.makeText(this, task.message, Toast.LENGTH_SHORT).show()
            }
    }

    override fun signUp(
        imageURL: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
            if (task.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
                return@addOnCompleteListener
        }
            .addOnFailureListener {task ->
                Toast.makeText(this, task.message, Toast.LENGTH_SHORT).show()
            }
    }

    override fun goToSignInPage() {
        fragment = SignInFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_current_fragment, fragment)
            .commit()
    }

    override fun goToSignUpPage() {
        fragment = SignUpFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_current_fragment, fragment)
            .commit()
    }

    private fun initFragment(firstFragment: Fragment) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.auth_current_fragment)

        if (currentFragment == null) {
            fragment = firstFragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.auth_current_fragment, fragment)
                .commit()
        }
    }
}