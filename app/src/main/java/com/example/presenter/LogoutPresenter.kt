package com.example.presenter

import com.example.model.ResponseLogoutCallback
import com.example.model.VolleyHandler
import com.example.model.remote.dto.LogoutResponse

class LogoutPresenter(private val volleyHandler: VolleyHandler,
                      private val logoutView: LogoutContract.LogoutView) :
    LogoutContract.LogoutPresenter{
    override fun performLogout(email: String) {
        volleyHandler.logout(email, object : ResponseLogoutCallback {
            override fun successLogout(logoutResponse: LogoutResponse) {
                logoutView.logoutSuccess(logoutResponse)
            }

            override fun failureLogout(error: String) {
                logoutView.logoutFailure(error)
            }

        })
    }
}