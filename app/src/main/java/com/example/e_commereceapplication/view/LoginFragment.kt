package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentLoginBinding
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.presenter.LoginPresenter
import com.example.e_commereceapplication.presenter.MVPShoppingCart

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loginPresenter = LoginPresenter(VolleyHandler(requireContext()), object : MVPShoppingCart.LoginView {
            override fun setError() {

            }

            override fun setSuccess() {

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, CategoryFragment())
                    ?.commit()
            }
        })

        binding.button.setOnClickListener {

            loginPresenter.validateUser("", "")
        }

        binding.tvNotHaveAccount.setOnClickListener {
            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container, RegisterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}

