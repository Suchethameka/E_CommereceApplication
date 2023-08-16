package com.example.e_commereceapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.databinding.ProductItemBinding
import com.example.e_commereceapplication.model.Network.VolleyConstants.Companion.IMAGE_URL
import com.example.e_commereceapplication.model.Network.productslist.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(private val products:List<Product>,val itemClickListener: ItemClickListener):
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            with(binding){
                productName.text = product.product_name
                productDescription.text = product.description
                productPrice.text = "$ ${product.price}"
                ratingBar.rating = product.average_rating.toFloat()
                val url = "$IMAGE_URL${product.product_image_url}"
                Picasso.get().load(url).into(imagePhone)

                binding.root.setOnClickListener {
                    itemClickListener.isSelected(product.product_id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(layoutInflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
}