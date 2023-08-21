package com.example.model.remote.dto

data class SubCategoryResponse(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)