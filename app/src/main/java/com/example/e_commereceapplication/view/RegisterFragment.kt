package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commereceapplication.databinding.FragmentRegisterBinding
import com.example.e_commereceapplication.model.Network.User.UserProfile
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.presenter.MVPShoppingCart
import com.example.e_commereceapplication.presenter.RegisterPresenter
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.setDisplayShowHomeEnabled(false)
        initRegister()
    }

    private fun initRegister() {
        presenter = RegisterPresenter(VolleyHandler(requireContext()),
            object : MVPShoppingCart.RegisterView {
                override fun setError() {
                    Snackbar.make(
                        binding.root,
                        "Failed to Register User",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                override fun setSuccess() {
                    activity?.supportFragmentManager?.popBackStack()
                }

            })
        binding.buttonRegister.setOnClickListener {
            with(binding) {
                val user = UserProfile(
                    fullName = etFullName.text.toString(),
                    phoneNumber = etMobileNum.text.toString(),
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
                presenter.registerUser(user)

            }
        }
        binding.tvHaveAccount.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

    }
}