package com.example.e_commereceapplication.model.Network.productslist

import com.example.e_commereceapplication.model.Network.productslist.Product

data class ProductListResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)