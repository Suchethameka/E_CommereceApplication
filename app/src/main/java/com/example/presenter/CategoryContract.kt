package com.example.presenter

import com.example.model.remote.dto.CategoryResponse

interface CategoryContract {

    interface CategoryView {
        fun categoriesSuccess(categoryResponse: CategoryResponse)
        fun categoriesError(message: String)
    }

    interface CategoryPresenter {
        fun getCategories()
    }
}