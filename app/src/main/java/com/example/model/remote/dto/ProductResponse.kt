package com.example.model.remote.dto

data class ProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)