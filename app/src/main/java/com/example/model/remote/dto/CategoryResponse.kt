package com.example.model.remote.dto

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)