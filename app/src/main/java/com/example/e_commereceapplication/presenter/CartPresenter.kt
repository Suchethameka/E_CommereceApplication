package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.database.CartDao

class CartPresenter(private val cartDao: CartDao):MVPShoppingCart.ICartPresenter {


    override fun getCartItems() = cartDao.fetchProduct()
}