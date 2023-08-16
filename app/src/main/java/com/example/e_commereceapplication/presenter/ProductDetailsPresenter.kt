package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.ResponseCallback
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.productDetailsModel.ProductDescriptionResponse

class ProductDetailsPresenter(val volleyHandler: VolleyHandler, val detailsView: MVPShoppingCart.ProductDetailsView)
    :MVPShoppingCart.IProductDetailsPresenter{
    override fun getProductDetails(productId: String) {
        volleyHandler.getProductDetails(productId,object: ResponseCallback {
            override fun success(response: Any?) {
                (response as? ProductDescriptionResponse)?.let{
                    detailsView.setSuccess(it)
                }
            }

            override fun failure() {
                detailsView.setError()
            }

        })
    }

}