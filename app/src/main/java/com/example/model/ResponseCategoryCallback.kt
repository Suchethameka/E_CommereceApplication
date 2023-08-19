package com.example.model

import com.example.model.remote.dto.CategoryResponse

interface ResponseCategoryCallback {

    fun successCategory(categoryResponse: CategoryResponse)

    fun failureCategory(error: String)
}