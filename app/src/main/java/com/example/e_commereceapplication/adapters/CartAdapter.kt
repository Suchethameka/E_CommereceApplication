package com.example.e_commereceapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.databinding.ItemCartBinding
import com.example.e_commereceapplication.model.Network.VolleyConstants
import com.example.e_commereceapplication.model.Network.cart.CartItem
import com.squareup.picasso.Picasso

class CartAdapter(private val cartList:List<CartItem>, private val showQuantityStepper: Boolean = true):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItem){
            with(binding){
                productName.text = cartItem.product_name
                productDescription.text = cartItem.description
                productPrice.text = "$ ${cartItem.price}"
                tvPriceTotal.text = cartItem.price
                quantityStepper.setQuantity(cartItem.quantity)
                quantityStepper.isVisible = showQuantityStepper
                val url = "${VolleyConstants.IMAGE_URL}${cartItem.product_image_url}"
                Picasso.get().load(url).into(imagePhone)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(layoutInflater,parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount() = cartList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }
}