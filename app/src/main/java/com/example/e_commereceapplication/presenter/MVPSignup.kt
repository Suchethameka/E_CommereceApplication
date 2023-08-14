package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.data.RegisterResponse
import com.example.e_commereceapplication.data.UserRegistrationData
import com.example.e_commereceapplication.data.UserX

interface MVPSignup {

    interface ISignupPresenter {
        fun registerUser(userRegistrationData: UserRegistrationData)
    }

    interface SignupView {
        fun showRegisterSuccess(response: RegisterResponse)
        fun showRegisterError(error: String)
    }
}



