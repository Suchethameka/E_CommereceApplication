package com.example.e_commereceapplication.model.Network.productDetailsModel

data class ProductDescriptionResponse(
    val message: String,
    val product: Product,
    val status: Int
)