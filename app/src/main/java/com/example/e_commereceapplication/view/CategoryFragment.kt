package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.adapters.CategoryAdapter
import com.example.e_commereceapplication.adapters.ItemClickListener
import com.example.e_commereceapplication.databinding.FragmentCategoryBinding
import com.example.e_commereceapplication.model.Network.VolleyHandler
import com.example.e_commereceapplication.model.Network.category.CategoryResponse
import com.example.e_commereceapplication.presenter.CategoryPresenter
import com.example.e_commereceapplication.presenter.MVPShoppingCart
import com.google.android.material.snackbar.Snackbar


class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryPresenter: CategoryPresenter
    private lateinit var subCategoryFragment: SubCategoryFragment

    private val itemClickListener = object : ItemClickListener {
        override fun isSelected(id: String) {
            val bundle = Bundle()
            bundle.putString("id", id)
            subCategoryFragment = SubCategoryFragment()
            subCategoryFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, subCategoryFragment)?.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryPresenter = CategoryPresenter(VolleyHandler(requireContext()), object :
            MVPShoppingCart.CategoryView {
            override fun setError() {
                if (isAdded) {
                    Snackbar.make(binding.root, "categories not found", Snackbar.LENGTH_LONG).show()
                }
            }

            override fun setSuccess(categoriesResponse: CategoryResponse) {
                if (isAdded) {
                    val adapter = CategoryAdapter(categoriesResponse.categories, itemClickListener)
                    binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvCategories.adapter = adapter
                }
            }
        })

        categoryPresenter.getCategories()
    }
}

