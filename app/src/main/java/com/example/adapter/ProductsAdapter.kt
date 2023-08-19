package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.ProductItemBinding
import com.example.model.local.DbHandler
import com.example.model.local.dao.ProductDao
import com.example.model.local.entity.ProductLocal
import com.example.model.remote.dto.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(private val context: Context,
                      private val products: MutableList<Product>,
                      private val itemProductClickListener: ItemProductClickListener) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private lateinit var dbHandler: DbHandler
    private lateinit var productDao: ProductDao
    interface ItemProductClickListener {
        fun onProductClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        initDao()

        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateData(newProducts: List<Product>) {
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener{
                val clickedProduct = products[adapterPosition]
                itemProductClickListener.onProductClick(clickedProduct)
            }
        }

        fun bind(product: Product){
            binding.apply {
                /*Picasso
                    .get()
                    .load(CategoryAdapter.URL_IMAGE +product.product_image_url)
                    .into(imgProduct)*/

                Picasso
                    .get()
                    .load(R.drawable.iphone)
                    .into(imgProduct)

                txtNameOfProduct.text = product.product_name
                txtDescriptionOfProduct.text = product.description
                txtPriceOfProduct.text = "$ ${product.price}"

                // here I will do all works with cart
                val productId = product.product_id.toString()

                var quantity = productDao.getProductQuantity(productId)

                if (quantity < 1) {
                    quantity = 1
                    binding.txtAddToCart.visibility = View.VISIBLE
                    binding.layoutAddToCart.visibility = View.GONE
                } else {
                    binding.txtAddToCart.visibility = View.GONE
                    binding.layoutAddToCart.visibility = View.VISIBLE
                    binding.txtQuantitiyCart.text = quantity.toString()
                }

                binding.txtAddToCart.setOnClickListener {
                    binding.txtAddToCart.visibility = View.GONE
                    binding.layoutAddToCart.visibility = View.VISIBLE


                    val productId = product.product_id.toString()
                    val productName = product.product_name.toString()
                    val productImage = product.product_image_url.toString()
                    val description = product.description.toString()
                    val price = product.price.toString()

                    val newProduct = ProductLocal(
                        product_id = productId,
                        product_name = productName,
                        price = price,
                        product_image_url = productImage,
                        description = description
                    )

                    productDao.addProduct(newProduct, quantity)
                }

                binding.btnMinus.setOnClickListener {
                    if (quantity > 1) {
                        quantity--
                        binding.txtQuantitiyCart.text = quantity.toString()
                        productDao.updateQuantity(productId, quantity)
                    } else {
                        binding.txtAddToCart.visibility = View.VISIBLE
                        binding.layoutAddToCart.visibility = View.GONE
                        productDao.deleteProductById(product.product_id.toString())
                    }
                }

                binding.btnPlus.setOnClickListener {
                    quantity++
                    binding.txtQuantitiyCart.text = quantity.toString()
                    productDao.updateQuantity(productId, quantity)
                }
            }
        }

        override fun onClick(v: View?) {
            itemProductClickListener.onProductClick(products[adapterPosition])
        }
    }

    private fun initDao() {
        dbHandler = DbHandler(context)
        productDao = ProductDao(dbHandler)
    }

    companion object {
        const val URL_IMAGE = "http://10.0.2.2/myshop/images/"
    }
}