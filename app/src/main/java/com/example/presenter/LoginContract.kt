package com.example.presenter

import com.example.model.remote.dto.LoginResponse

interface LoginContract {
    interface LoginView {
        fun loginSuccess(loginResponse: LoginResponse)
        fun loginFailure(error: String)
        fun navigateToRegistrationFragment()
    }

    interface LoginPresenter {
        fun performLogin(email: String, password: String)
        fun onNoAccountLinkClicked()
    }
}