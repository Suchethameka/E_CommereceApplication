package com.example.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.view.adapter.ProductsAdapter
import com.example.view.activity.MainActivity
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentSubCategoryProductsBinding
import com.example.model.remote.dto.Product
import com.example.model.remote.dto.ProductResponse
import com.google.gson.Gson

class SubCategoryProductsFragment : Fragment(), ProductsAdapter.ItemProductClickListener {

    private lateinit var binding: FragmentSubCategoryProductsBinding
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubCategoryProductsBinding.inflate(inflater, container, false)
        val subcategoryId = arguments?.getString("subcategoryId")

        productsAdapter = ProductsAdapter(requireContext(), mutableListOf(), this)
        binding.productRecyclerView.adapter = productsAdapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        getProducts(subcategoryId)

        return binding.root
    }

    private fun getProducts(subcategoryId: String?) {
        val request = JsonObjectRequest(
            Request.Method.GET, URL +subcategoryId, null,
            { response ->
                val productResponse = Gson().fromJson(response.toString(), ProductResponse::class.java)

                if (productResponse.status == 0) {
                    val products = productResponse.products
                    productsAdapter.updateData(products)
                }
            },
            { error ->
                println("Error: ${error.message}")
            })
        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)
    }

    override fun onProductClick(product: Product) {
        val bundle = Bundle()
        bundle.putString("productId", product.product_id)

        val productDetailFragment = ProductDetailsFragment()
        productDetailFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.productDetail_fragment, productDetailFragment)
            .addToBackStack(null)
            .commit()

        (activity as? MainActivity)?.updateToolbar("Details")
    }

    companion object {
        fun newInstance(subcategoryId: String): SubCategoryProductsFragment {
            val fragment = SubCategoryProductsFragment()
            val args = Bundle()
            args.putString("subcategoryId", subcategoryId)
            fragment.arguments = args
            return fragment
        }

        const val URL = "http://10.0.2.2/myshop/index.php/SubCategory/products/"
    }
}