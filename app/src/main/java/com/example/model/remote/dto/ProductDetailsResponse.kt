package com.example.model.remote.dto

data class ProductDetailsResponse(
    val message: String,
    val product: ProductDetails,
    val status: Int
)