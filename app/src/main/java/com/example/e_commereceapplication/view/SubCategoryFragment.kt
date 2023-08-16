package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.adapters.SubCategoryViewPageAdapter
import com.example.e_commereceapplication.databinding.CustomTablayoutBinding
import com.example.e_commereceapplication.databinding.FragmentSubCategoryBinding
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.subCategory.SubcategoryResponse
import com.example.e_commereceapplication.presenter.MVPShoppingCart
import com.example.e_commereceapplication.presenter.SubCategoryPresenter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class SubCategoryFragment : Fragment() {
    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var presenter: SubCategoryPresenter
    private lateinit var subCategoryViewPageAdapter: SubCategoryViewPageAdapter
    private var id : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString("id")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubCategoryBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SubCategoryPresenter(VolleyHandler(requireContext()),object: MVPShoppingCart.SubCategoryView{
            override fun setError() {
                Snackbar.make(binding.root,"Error fetching data", Snackbar.LENGTH_LONG).show()
            }

            override fun setSuccess(subcategoryResponse: SubcategoryResponse) {
                initTabs(subcategoryResponse)

            }


        })
        id?.let {
            presenter.getSubCategories(it)
        }




    }

    fun initTabs(subcategoryResponse: SubcategoryResponse){

        val fragments = mutableListOf<ProductListFragment>()
        subcategoryResponse.subcategories.forEach {
            val bundle = Bundle()
            bundle.putString("sub_category_id", it.subcategory_id)
            val fragment = ProductListFragment()
            fragment.arguments = bundle
            fragments.add(fragment)

        }
        subCategoryViewPageAdapter = SubCategoryViewPageAdapter(fragments, requireActivity())

        with(binding){
            viewPager2.adapter = subCategoryViewPageAdapter

            TabLayoutMediator(tabLayout, viewPager2){
                    tab, position ->
                val tabBinding = CustomTablayoutBinding.inflate(layoutInflater,tabLayout,false)
                with(tabBinding){
                    subcategoryResponse.subcategories.forEachIndexed { index, subcategory ->
                        if(position == index) {
                            tvTabText.text = subcategory.subcategory_name
                        }
                    }
                }
                tab.customView = tabBinding.root


            }.attach()
        }
    }



}