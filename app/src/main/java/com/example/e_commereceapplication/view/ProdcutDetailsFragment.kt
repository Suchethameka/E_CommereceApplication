package com.example.e_commereceapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.adapters.ImageViewAdapter
import com.example.e_commereceapplication.adapters.ReviewsAdapter
import com.example.e_commereceapplication.adapters.SpecificationAdapter
import com.example.e_commereceapplication.databinding.FragmentProdcutDetailsBinding
import com.example.e_commereceapplication.model.Network.VolleyConstants
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.cart.CartItem
import com.example.e_commereceapplication.model.Network.database.AppDatabase
import com.example.e_commereceapplication.model.Network.database.CartDao
import com.example.e_commereceapplication.model.Network.productDetailsModel.Product
import com.example.e_commereceapplication.model.Network.productDetailsModel.ProductDescriptionResponse
import com.example.e_commereceapplication.model.Network.productDetailsModel.Review
import com.example.e_commereceapplication.presenter.MVPShoppingCart
import com.example.e_commereceapplication.presenter.ProductDetailsPresenter

class ProductDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProdcutDetailsBinding
    private lateinit var presenter: ProductDetailsPresenter
    private lateinit var cartDao: CartDao
    private lateinit var viewAdapter: ImageViewAdapter
    private var productId:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = arguments?.getString("productId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProdcutDetailsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? ShoppingCartActivity)?.showBackButton()
        initDB()
        presenter = ProductDetailsPresenter(VolleyHandler(requireContext()), cartDao,object:MVPShoppingCart.ProductDetailsView{
            override fun setError() {
                TODO("Not yet implemented")
            }

            @SuppressLint("SetTextI18n")
            override fun setSuccess(productDescriptionResponse: ProductDescriptionResponse) {
                //productDescriptionResponse
                with(binding){
                    productDName.text = productDescriptionResponse.product.product_name
                    productDDescription.text = productDescriptionResponse.product.description
                    ratingBar.rating = productDescriptionResponse.product.average_rating.toFloat()
                    productDPrice.text = "$ ${productDescriptionResponse.product.price}"


                }

                binding.addToCart.setOnClickListener {
                    with(productDescriptionResponse.product){
                        addToCart(this, 1)
                    }
                    it.isVisible = false
                    binding.quantityStepper.isVisible = true
                }

                presenter.getProductWithId(productDescriptionResponse.product.product_id)?.let {
                    binding.quantityStepper.setQuantity(it.quantity)
                    binding.addToCart.isVisible = false
                    binding.quantityStepper.isVisible = true
                }

                binding.quantityStepper.setQuantityStepperListener(object : QuantityStepperListener{
                    override fun onQuantityChanged(quantity: Int) {
                        with(productDescriptionResponse.product){
                            addToCart(this, quantity)
                        }

                    }

                    override fun onQuantityZero() {
                        with(productDescriptionResponse.product){
                            val cartItem = CartItem(
                                id=product_id,
                                unitPrice = price,
                                product_image_url= product_image_url,
                                product_name = product_name,
                                description = description

                            )
                            presenter.deleteItemInCart(cartItem)
                        }
                        binding.addToCart.isVisible = true
                        binding.quantityStepper.isVisible = false
                    }

                })

                val adapter = SpecificationAdapter(productDescriptionResponse.product.specifications)
                binding.rvSpecifications.layoutManager = LinearLayoutManager(requireContext())
                binding.rvSpecifications.adapter = adapter

                val reviewAdapter = ReviewsAdapter(productDescriptionResponse.product.reviews as List<Review>)
                binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
                binding.rvReviews.adapter = reviewAdapter

                viewAdapter = ImageViewAdapter(productDescriptionResponse.product.images, requireContext())
                binding.viewPagerImage.adapter = viewAdapter

            }



        })

        productId?.let{
            presenter.getProductDetails(it)
        }
    }

    private fun initDB() {
        val database = AppDatabase.getAppDatabase(requireContext())
        cartDao = database.getCartDao()
    }

    override fun onResume() {
        super.onResume()
        (activity as? ShoppingCartActivity)?.onChangeToolbarTitle("Details")
    }

    fun addToCart(product: Product, quantity:Int){
        with(product) {
            val cartItem = CartItem(
                id = product_id,
                unitPrice = price,
                product_image_url = product_image_url,
                product_name = product_name,
                description = description,
                quantity = quantity

            )
            presenter.addToCart(cartItem)
        }
    }

}