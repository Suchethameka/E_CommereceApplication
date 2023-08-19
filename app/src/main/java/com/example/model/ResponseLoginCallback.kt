package com.example.model

import com.example.model.remote.dto.LoginResponse

interface ResponseLoginCallback {
    fun successLogin(loginResponse: LoginResponse)
    fun failureLogin(error: String)
}