package com.example.e_commereceapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.e_commereceapplication.data.RegisterResponse
import com.example.e_commereceapplication.data.UserRegistrationData
import com.example.e_commereceapplication.data.UserX
import com.example.e_commereceapplication.databinding.FragmentSignupBinding
import com.example.e_commereceapplication.model.VolleyHandler
import com.example.e_commereceapplication.presenter.MVPSignup
import com.example.e_commereceapplication.presenter.SignupPresenter

class SignupFragment : Fragment(), MVPSignup.SignupView {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var presenter: MVPSignup.ISignupPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        presenter = SignupPresenter(VolleyHandler(requireContext()), this)

        binding.buttonSignUp.setOnClickListener {
            val fullName = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val phoneNumber = binding.editTextPhoneNumber.text.toString()
            val password = binding.editTextCreatePassword.text.toString()

            val userRegistrationData = UserRegistrationData(fullName, email, phoneNumber, password)

            presenter.registerUser(userRegistrationData)
        }

        return binding.root
    }

    override fun showRegisterSuccess(response: RegisterResponse) {
        Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun showRegisterError(error: String) {
        Toast.makeText(requireContext(), "Registration failed: $error", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG: String = "SignupFragment"

        fun newInstance(): SignupFragment {
            return SignupFragment()
        }
    }
}








