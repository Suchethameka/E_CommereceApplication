package com.example.view.fragment.registrationfragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentLoginBinding
import com.example.model.VolleyHandler
import com.example.model.preferences.SharedPreference
import com.example.model.remote.dto.LoginResponse
import com.example.presenter.LoginContract
import com.example.presenter.LoginPresenter
import com.example.view.activity.MainActivity

class LoginFragment : Fragment(), LoginContract.LoginView {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginPresenter: LoginContract.LoginPresenter
    private lateinit var sharedPreference : SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPresenter = LoginPresenter(VolleyHandler(requireContext()),this)

        binding.txtNoAccount.setOnClickListener {
            loginPresenter.onNoAccountLinkClicked()
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun loginSuccess(loginResponse: LoginResponse) {
        Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    override fun loginFailure(message: String) {
        Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
        Log.i("TAG", message)
    }

    override fun navigateToRegistrationFragment() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.registration_container, RegisterFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}