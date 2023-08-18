package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.adapters.CartAdapter
import com.example.e_commereceapplication.databinding.FragmentCartBinding
import com.example.e_commereceapplication.model.Network.database.AppDatabase
import com.example.e_commereceapplication.model.Network.database.CartDao
import com.example.e_commereceapplication.presenter.CartPresenter


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDB()
        val presenter = CartPresenter(cartDao)
        val adapter = CartAdapter(presenter.getCartItems())
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = adapter
    }
    private fun initDB(){
        appDatabase = AppDatabase.getAppDatabase(activity?.applicationContext!!)!!
        cartDao = appDatabase.getCartDao()
    }


}