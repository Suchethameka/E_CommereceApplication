package com.example.e_commereceapplication.data

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)