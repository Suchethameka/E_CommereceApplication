package com.example.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.model.remote.dto.Subcategory
import com.example.view.SubCategoryProductsFragment

class SubCategoryAdapter(fragment: Fragment, private val subcategories: List<Subcategory>, )
    : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = subcategories.size
    override fun createFragment(position: Int): Fragment {
        return SubCategoryProductsFragment.newInstance(subcategories[position].subcategory_id)
    }
}