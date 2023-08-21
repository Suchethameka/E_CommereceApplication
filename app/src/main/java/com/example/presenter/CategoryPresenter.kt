package com.example.presenter

import com.example.model.ResponseCategoryCallback
import com.example.model.VolleyHandler
import com.example.model.remote.dto.CategoryResponse

class CategoryPresenter(private val volleyHandler: VolleyHandler,
                        private val categoryView: CategoryContract.CategoryView):
    CategoryContract.CategoryPresenter {
    override fun getCategories() {
        volleyHandler.getCategories(object : ResponseCategoryCallback {

            override fun successCategory(categoryResponse: CategoryResponse) {
                categoryView.categoriesSuccess(categoryResponse)
            }

            override fun failureCategory(error: String) {
                categoryView.categoriesError(error)
            }

        })
    }
}