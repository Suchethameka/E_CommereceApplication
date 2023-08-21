package com.example.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.CartProductItemBinding
import com.example.model.local.DbHandler
import com.example.model.local.dao.ProductDao
import com.example.model.local.entity.ProductLocal
import com.squareup.picasso.Picasso


class CartProductsAdapter(private val context: Context,
                          private val products: MutableList<ProductLocal>) :
    RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    private lateinit var dbHandler: DbHandler
    private lateinit var productDao: ProductDao

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        initDao()
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun updateProducts(newProducts: List<ProductLocal>) {
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(private val binding: CartProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productLocal: ProductLocal) {
            binding.apply {

                Picasso
                    .get()
                    .load(R.drawable.iphone)
                    .into(imgProduct)
                txtNameOfProduct.text = productLocal.product_name
                txtDescriptionOfProduct.text = productLocal.description
                txtPriceOfProduct.text = "$ ${productLocal.price}"

                var totalPriceNumber = productLocal.total_price
                val totalPrice = productLocal.total_price.toString()
                txtTotalPriceOfProduct.text = totalPrice

                val productId = productLocal.product_id

                var quantity = productDao.getProductQuantity(productId)

                if (quantity < 1) {
                    quantity = 1

                    val newTotalPrice = totalPriceNumber + productLocal.price.toDouble()
                    txtTotalPriceOfProduct.text = newTotalPrice.toString()

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

                    val newTotalPrice = totalPriceNumber + productLocal.price.toDouble()
                    txtTotalPriceOfProduct.text = newTotalPrice.toString()

                    val productId = productLocal.product_id
                    val productName = productLocal.product_name
                    val productImage = productLocal.product_image_url
                    val description = productLocal.description
                    val price = productLocal.price

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

                        val newTotalPrice = totalPriceNumber - productLocal.price.toDouble()
                        totalPriceNumber = newTotalPrice
                        txtTotalPriceOfProduct.text = newTotalPrice.toString()

                        productDao.updateQuantity(productId, quantity)
                    } else {
                        binding.txtAddToCart.visibility = View.VISIBLE
                        binding.layoutAddToCart.visibility = View.GONE
                        val newTotalPrice = totalPriceNumber - productLocal.price.toDouble()
                        totalPriceNumber = newTotalPrice
                        txtTotalPriceOfProduct.text = newTotalPrice.toString()
                        productDao.deleteProductById(productLocal.product_id)
                    }
                }

                binding.btnPlus.setOnClickListener {
                    quantity++
                    binding.txtQuantitiyCart.text = quantity.toString()

                    val newTotalPrice = totalPriceNumber + productLocal.price.toDouble()
                    totalPriceNumber = newTotalPrice
                    txtTotalPriceOfProduct.text = newTotalPrice.toString()

                    productDao.updateQuantity(productId, quantity)
                }
            }
        }
    }

    private fun initDao() {
        dbHandler = DbHandler(context)
        productDao = ProductDao(dbHandler)
    }
}