package com.example.model.remote.dto

data class LoginResponse(
    val status: Int,
    val message: String,
    val user: User?
)