package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.adapters.CartItemsAdapter
import com.example.e_commereceapplication.databinding.FragmentCartItemsBinding
import com.example.e_commereceapplication.model.Network.database.AppDatabase
import com.example.e_commereceapplication.model.Network.database.CartDao
import com.example.e_commereceapplication.presenter.CartItemPresenter

class CartItemsFragment : Fragment() {

    private lateinit var binding: FragmentCartItemsBinding
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var presenter: CartItemPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartItemsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDB()
    }

    private fun initDB(){
        appDatabase = AppDatabase.getAppDatabase(activity?.applicationContext!!)!!
        cartDao = appDatabase.getCartDao()

        presenter = (CartItemPresenter(cartDao))

        val adapter = CartItemsAdapter(presenter.getCartItems())
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = adapter
    }

}