package com.example.e_commereceapplication.model

import com.example.e_commereceapplication.data.RegisterResponse

interface SignupResponseCallback {
    fun onSignupSuccess(response: RegisterResponse)
    fun onSignupFailure(error: String)
}
