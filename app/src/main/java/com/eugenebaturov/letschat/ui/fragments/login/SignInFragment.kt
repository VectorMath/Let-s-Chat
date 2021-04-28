package com.eugenebaturov.letschat.ui.fragments.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.eugenebaturov.letschat.R

class SignInFragment: Fragment() {

    interface Callbacks {
        fun goToSignUpPage()
        fun signIn(email: String, password: String)
    }

    private lateinit var textViewSignUpLink: TextView
    private lateinit var buttonSignIn: Button
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        initViews(view)
        setOnClickListeners()

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun initViews(view: View) {
        textViewSignUpLink = view.findViewById(R.id.tv_link_to_sign_up)
        buttonSignIn = view.findViewById(R.id.button_sign_in)
        editTextEmail = view.findViewById(R.id.et_user_email)
        editTextPassword = view.findViewById(R.id.et_user_password)
    }

    private fun setOnClickListeners() {
        textViewSignUpLink.setOnClickListener {
            callbacks?.goToSignUpPage()
        }

        buttonSignIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            callbacks?.signIn(email, password)
        }
    }
}