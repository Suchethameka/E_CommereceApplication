package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.ResponseCallback
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.category.CategoryResponse

class CategoryPresenter(private val volleyHandler: VolleyHandler, val categoryView: MVPShoppingCart.CategoryView)
    :MVPShoppingCart.ICategoryPresenter {
    override fun getCategories() {
        volleyHandler.getCategories(object: ResponseCallback {

            override fun success(response: Any?) {
                (response as? CategoryResponse)?.let {
                    categoryView.setSuccess(it)
                }
            }

            override fun failure() {
                categoryView.setError()
            }

        })
    }

}