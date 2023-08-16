package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.ResponseCallback
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.productslist.ProductListResponse

class ProductsListPresenter(private val volleyHandler: VolleyHandler, val productsView: MVPShoppingCart.ProductView)
    : MVPShoppingCart.IProductListPresenter{
    override fun getProducts(subCatId: String) {
        volleyHandler.getProducts(subCatId,object: ResponseCallback {

            override fun success(response: Any?) {
                (response as? ProductListResponse)?.let {
                    productsView.setSuccess(it)
                }
            }

            override fun failure() {
                productsView.setError()
            }

        })
    }
}