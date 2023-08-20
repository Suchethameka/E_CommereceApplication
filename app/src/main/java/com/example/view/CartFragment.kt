package com.example.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.view.adapter.CartProductsAdapter
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentCartBinding
import com.example.model.local.DbHandler
import com.example.model.local.dao.ProductDao

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var dbHandler: DbHandler
    private lateinit var productDao: ProductDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDao()
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cart = productDao.getAllProducts()

        val productLocalAdapter = CartProductsAdapter(requireContext(), cart)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rcProducts.adapter = productLocalAdapter
        binding.rcProducts.layoutManager = layoutManager

        binding.txtTotal.text = "$ ${productDao.calculateTotalPriceInCart().toString()}"

        binding.btnCheckout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, CheckoutFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initDao() {
        dbHandler = DbHandler(requireContext())
        productDao = ProductDao(dbHandler)
    }
}