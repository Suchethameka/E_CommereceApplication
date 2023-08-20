package com.example.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.CheckoutItemBinding
import com.example.model.local.entity.ProductLocal
import com.squareup.picasso.Picasso

class CheckoutItemAdapter(private val products: MutableList<ProductLocal>) :
    RecyclerView.Adapter<CheckoutItemAdapter.CheckoutViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CheckoutItemBinding.inflate(layoutInflater, parent, false)
        return CheckoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    inner class CheckoutViewHolder(private val binding: CheckoutItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(productLocal: ProductLocal){
            binding.apply {
                Picasso
                    .get()
                    .load(R.drawable.iphone)
                    .into(imgProduct)
                txtNameOfProduct.text = productLocal.product_name
                txtUnitPriceHolder.text = "$ ${productLocal.price}"
                txtQuantityHolder.text = productLocal.quantity.toString()
                txtAmountHolder.text = "$ ${productLocal.total_price}"
            }
        }

    }
}