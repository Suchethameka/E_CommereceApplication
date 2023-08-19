package com.example.presenter

import com.example.model.ResponseLoginCallback
import com.example.model.VolleyHandler
import com.example.model.remote.dto.LoginResponse

class LoginPresenter(private val volleyHandler: VolleyHandler,
                     private val loginView: LoginContract.LoginView) :
    LoginContract.LoginPresenter {

    override fun performLogin(email: String, password: String) {
        volleyHandler.login(email, password, responseLoginCallback = object :
            ResponseLoginCallback {

            override fun successLogin(loginResponse: LoginResponse) {
                loginView.loginSuccess(loginResponse)
            }

            override fun failureLogin(error: String) {
                loginView.loginFailure(error)
            }
        })
    }

    override fun onNoAccountLinkClicked() {
        loginView.navigateToRegistrationFragment()
    }
}