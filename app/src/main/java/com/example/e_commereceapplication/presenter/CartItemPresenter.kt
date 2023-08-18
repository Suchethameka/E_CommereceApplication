package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.cart.CartItem
import com.example.e_commereceapplication.model.Network.database.CartDao

class CartItemPresenter(private val cartDao: CartDao): MVPShoppingCart.ICartPresenter{
    override fun getCartItems(): List<CartItem> {
        return cartDao.fetchProduct()
    }
}