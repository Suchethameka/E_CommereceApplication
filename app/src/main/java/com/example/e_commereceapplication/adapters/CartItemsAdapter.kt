package com.example.e_commereceapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.databinding.ItemCheckoutCartBinding
import com.example.e_commereceapplication.model.Network.VolleyConstants
import com.example.e_commereceapplication.model.Network.cart.CartItem
import com.squareup.picasso.Picasso

class CartItemsAdapter(private val cartList:List<CartItem>): RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>() {

    inner class CartItemViewHolder(val binding: ItemCheckoutCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            with(binding) {
                productName.text = cartItem.product_name
                productPrice.text = "$ ${cartItem.price}"
                productUnitPrice.text = cartItem.price
                productQuantity.text = cartItem.quantity.toString()
                val url = "${VolleyConstants.IMAGE_URL}${cartItem.product_image_url}"
                Picasso.get().load(url).into(imagePhone)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CartItemViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCheckoutCartBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount() = cartList.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(cartList[position])
    }
}