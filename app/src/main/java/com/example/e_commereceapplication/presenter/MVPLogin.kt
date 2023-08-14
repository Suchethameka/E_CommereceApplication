package com.example.e_commereceapplication.presenter

import android.content.Context
import com.example.e_commereceapplication.data.UserResponse


interface MVPLogin {

    interface ILoginPresenter {
        fun loginUser(email: String, password: String)
        fun startMainActivity()
        fun openSignUpFragment()
    }

    interface LoginView {
        fun showLoginSuccess()
        fun showLoginError(error: String)
        fun getContext(): Context
        fun finishActivity()
    }
}





