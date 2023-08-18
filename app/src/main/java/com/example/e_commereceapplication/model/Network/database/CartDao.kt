package com.example.e_commereceapplication.model.Network.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.e_commereceapplication.model.Network.cart.CartItem

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(cartItem: CartItem)

    @Query("SELECT * FROM CartItem" )
    fun fetchProduct():List<CartItem>

    @Query("SELECT * FROM CartItem WHERE id=:productId" )
    fun fetchProductWithId(productId: String): CartItem?

    @Update
    fun updateProduct(cartItem: CartItem)

    @Delete
    fun deleteProduct(cartItem: CartItem)
}