package com.example.e_commereceapplication.model

import com.example.e_commereceapplication.data.UserResponse

interface LoginResponseCallback {
    fun onLoginSuccess(response: UserResponse)
    fun onLoginFailure(error: String)
}
