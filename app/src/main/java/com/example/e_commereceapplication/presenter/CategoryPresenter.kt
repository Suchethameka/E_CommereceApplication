package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.data.CategoryResponse
import com.example.e_commereceapplication.model.CategoryResponseCallback
import com.example.e_commereceapplication.model.VolleyHandler

class CategoryPresenter(private val view: MVPCategory.CategoryView, private val volleyHandler: VolleyHandler) : MVPCategory.ICategoryPresenter {

    override fun fetchCategories() {
        volleyHandler.fetchCategories(object : CategoryResponseCallback {
            override fun onCategoryFetchSuccess(response: CategoryResponse) {
                view.showCategoryList(response.categories)
            }

            override fun onCategoryFetchFailure(error: String) {
                view.showCategoryFetchError(error)
            }
        })
    }
}