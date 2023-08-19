package com.example.model

import com.example.model.remote.dto.LogoutResponse

interface ResponseLogoutCallback {
    fun successLogout(logoutResponse: LogoutResponse)
    fun failureLogout(error: String)
}