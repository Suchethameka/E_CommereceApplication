package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.data.RegisterResponse
import com.example.e_commereceapplication.data.UserRegistrationData
import com.example.e_commereceapplication.model.SignupResponseCallback
import com.example.e_commereceapplication.model.VolleyHandler

class SignupPresenter(
    private val volleyHandler: VolleyHandler,
    private val signupView: MVPSignup.SignupView
) : MVPSignup.ISignupPresenter {

    override fun registerUser(userRegistrationData: UserRegistrationData) {
        volleyHandler.performSignup(userRegistrationData, object : SignupResponseCallback {
            override fun onSignupSuccess(response: RegisterResponse) {
                signupView.showRegisterSuccess(response)
            }

            override fun onSignupFailure(error: String) {
                signupView.showRegisterError(error)
            }
        })
    }
}






