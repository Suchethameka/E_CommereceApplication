package com.example.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.view.adapter.CheckoutItemAdapter
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentSummaryBinding
import com.example.model.local.DbHandler
import com.example.model.local.dao.ProductDao

class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var dbHandler: DbHandler
    private lateinit var productDao: ProductDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDao()
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = productDao.getAllProducts()

        val adapter = CheckoutItemAdapter(products)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.apply {
            rcProducts.adapter = adapter
            rcProducts.layoutManager = layoutManager
        }


        binding.apply {
            txtTotalBillHolder.text = "$ ${productDao.calculateTotalPriceInCart()}"
        }

    }

    private fun initDao() {
        dbHandler = DbHandler(requireContext())
        productDao = ProductDao(dbHandler)
    }
}