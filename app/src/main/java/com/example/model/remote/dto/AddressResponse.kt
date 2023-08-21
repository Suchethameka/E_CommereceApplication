package com.example.model.remote.dto

data class AddressResponse(
    val addresses: List<Address>,
    val message: String,
    val status: Int
)