package com.example.e_commereceapplication.model.Network.subCategory

import com.example.e_commereceapplication.model.Network.subCategory.Subcategory

data class SubcategoryResponse(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)