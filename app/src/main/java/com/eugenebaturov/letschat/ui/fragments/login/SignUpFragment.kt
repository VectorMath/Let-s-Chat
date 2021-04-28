package com.eugenebaturov.letschat.ui.fragments.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.eugenebaturov.letschat.R

class SignUpFragment : Fragment() {

    interface Callbacks {
        fun goToSignInPage()
        fun signUp(
            imageURL: String,
            username: String,
            email: String,
            password: String,
            confirmPassword: String
        )
    }

    private lateinit var textViewSignInLink: TextView
    private lateinit var buttonSignUp: Button
    private lateinit var imageViewAvatar: ImageView
    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText

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
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        initViews(view)
        setOnClickListeners()

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun initViews(view: View) {
        textViewSignInLink = view.findViewById(R.id.tv_link_to_sign_in)
        buttonSignUp = view.findViewById(R.id.button_sign_up)
        editTextUsername = view.findViewById(R.id.et_username)
        editTextEmail = view.findViewById(R.id.et_user_email)
        editTextPassword = view.findViewById(R.id.et_user_password)
        editTextConfirmPassword = view.findViewById(R.id.et_user_confirm_password)
        imageViewAvatar = view.findViewById(R.id.iv_user_avatar)
    }

    private fun setOnClickListeners() {
        textViewSignInLink.setOnClickListener {
            callbacks?.goToSignInPage()
        }

        buttonSignUp.setOnClickListener {
            val imageUrl = ""
            val username = editTextUsername.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()

            callbacks?.signUp(imageUrl, username, email, password, confirmPassword)
        }
    }
}