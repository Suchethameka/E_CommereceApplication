package com.example.e_commereceapplication.model.Network.category

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)