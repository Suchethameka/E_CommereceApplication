package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.adapters.ItemClickListener
import com.example.e_commereceapplication.adapters.ProductsAdapter
import com.example.e_commereceapplication.databinding.FragmentProductListBinding
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.productslist.ProductListResponse
import com.example.e_commereceapplication.presenter.MVPShoppingCart
import com.example.e_commereceapplication.presenter.ProductsListPresenter

class ProductListFragment : Fragment(), ItemClickListener {

    private lateinit var binding : FragmentProductListBinding
    private var subCategoryId: String? = null
    private lateinit var productsListPresenter: ProductsListPresenter
    private lateinit var productDetailsFragment: ProdcutDetailsFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subCategoryId = arguments?.getString("sub_category_id")


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsListPresenter = ProductsListPresenter(VolleyHandler(requireContext()),object:
            MVPShoppingCart.ProductView{
            override fun setError() {

            }

            override fun setSuccess(productListResponse: ProductListResponse) {
                val adapter = ProductsAdapter(productListResponse.products,this@ProductListFragment)
                binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
                binding.rvProducts.adapter = adapter
            }

        })

        subCategoryId?.let{
            productsListPresenter.getProducts(it)
        }



    }

    override fun isSelected(id: String) {
        productDetailsFragment = ProdcutDetailsFragment()
        val bundle = Bundle()
        bundle.putString("productId",id)
        productDetailsFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,productDetailsFragment)?.commit()
    }

}