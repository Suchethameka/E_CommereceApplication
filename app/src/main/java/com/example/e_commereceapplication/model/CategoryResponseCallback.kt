package com.example.e_commereceapplication.model

import com.example.e_commereceapplication.data.CategoryResponse

interface CategoryResponseCallback {
    fun onCategoryFetchSuccess(response: CategoryResponse)
    fun onCategoryFetchFailure(error: String)
}