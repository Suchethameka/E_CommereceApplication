package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.ResponseCallback
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.cart.CartItem
import com.example.e_commereceapplication.model.Network.database.CartDao
import com.example.e_commereceapplication.model.Network.productDetailsModel.ProductDescriptionResponse

class ProductDetailsPresenter(private val volleyHandler: VolleyHandler, private val cartDao: CartDao, val detailsView: MVPShoppingCart.ProductDetailsView)
    :MVPShoppingCart.IProductDetailsPresenter{
    override fun getProductDetails(productId: String) {
        volleyHandler.getProductDetails(productId,object:ResponseCallback{
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

    override fun addToCart(cartItem: CartItem) {
        cartDao.insertProduct(cartItem)
    }

    override fun deleteItemInCart(cartItem: CartItem) {
        cartDao.deleteProduct(cartItem)
    }

    override fun getProductWithId(productId: String): CartItem? {
        return cartDao.fetchProductWithId(productId)
    }

}