package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.ResponseCallback
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.subCategory.SubcategoryResponse

class SubCategoryPresenter(private val volleyHandler: VolleyHandler, val subCategoryView:MVPShoppingCart.SubCategoryView)
    :MVPShoppingCart.ISubCategoryPresenter{
    override fun getSubCategories(catId:String) {
        volleyHandler.getSubCategories(catId, object: ResponseCallback {

            override fun success(response: Any?) {
                (response as? SubcategoryResponse)?.let {
                    subCategoryView.setSuccess(it)
                }
            }

            override fun failure() {
                subCategoryView.setError()
            }

        })
    }
}