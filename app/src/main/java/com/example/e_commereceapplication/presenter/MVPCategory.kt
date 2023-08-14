package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.data.Category

interface MVPCategory {
    interface CategoryView {
        fun showCategoryList(categories: List<Category>)
        fun showCategoryFetchError(error: String)
    }

    interface ICategoryPresenter {
        fun fetchCategories()
    }
}