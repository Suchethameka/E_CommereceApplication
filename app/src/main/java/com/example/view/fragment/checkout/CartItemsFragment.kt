package com.example.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.view.adapter.CheckoutItemAdapter
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentCartItemsBinding
import com.example.model.local.DbHandler
import com.example.model.local.dao.ProductDao


class CartItemsFragment : Fragment() {

    private lateinit var binding: FragmentCartItemsBinding
    private lateinit var dbHandler: DbHandler
    private lateinit var productDao: ProductDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDao()
        binding = FragmentCartItemsBinding.inflate(inflater, container, false)
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

            txtTotal.text = "$ ${productDao.calculateTotalPriceInCart()}"

            binding.btnNext.setOnClickListener {
                val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)
                val currentItem = viewPager.currentItem
                viewPager.setCurrentItem(currentItem + 1, true)
            }

        }
    }

    private fun initDao() {
        dbHandler = DbHandler(requireContext())
        productDao = ProductDao(dbHandler)
    }
}