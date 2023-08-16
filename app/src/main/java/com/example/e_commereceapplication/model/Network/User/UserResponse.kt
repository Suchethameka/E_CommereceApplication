package com.example.e_commereceapplication.model.Network.User

data class UserResponse(
    val message: String,
    val status: Int,
    val user: UserX
)