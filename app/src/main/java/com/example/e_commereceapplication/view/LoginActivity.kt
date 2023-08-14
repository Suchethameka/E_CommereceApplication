package com.example.e_commereceapplication.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.e_commereceapplication.data.UserResponse
import com.example.e_commereceapplication.databinding.ActivityLoginBinding
import com.example.e_commereceapplication.fragments.SignupFragment
import com.example.e_commereceapplication.presenter.MVPLogin
import com.example.e_commereceapplication.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), MVPLogin.LoginView {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: MVPLogin.ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this)

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextLoginId.text.toString()
            val password = binding.editTextPassword.text.toString()

            presenter.loginUser(email, password)
        }

        binding.textViewSignUp.setOnClickListener {
            presenter.openSignUpFragment()
        }

    }


    override fun showLoginSuccess() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        presenter.startMainActivity()
    }

    override fun showLoginError(error: String) {
        Toast.makeText(this, "Login failed: $error", Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return this
    }

    override fun finishActivity() {
        finish()
    }


}








