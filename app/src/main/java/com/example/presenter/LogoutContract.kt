package com.example.presenter

import com.example.model.remote.dto.LogoutResponse

interface LogoutContract {

    interface LogoutView {
        fun logoutSuccess(logoutResponse: LogoutResponse)
        fun logoutFailure(error: String)
    }

    interface LogoutPresenter {
        fun performLogout(email: String)
    }
}