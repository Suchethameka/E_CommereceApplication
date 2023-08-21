package com.example.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.view.adapter.CategoryAdapter
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentHomeBinding
import com.example.model.VolleyHandler
import com.example.model.remote.dto.Category
import com.example.model.remote.dto.CategoryResponse
import com.example.presenter.CategoryContract
import com.example.presenter.CategoryPresenter
import com.example.view.activity.MainActivity

class HomeFragment : Fragment(), CategoryContract.CategoryView, CategoryAdapter.ItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: CategoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = CategoryPresenter(VolleyHandler(requireContext()), this)
        presenter.getCategories()
    }

    override fun onResume() {
        super.onResume()

        (activity as? MainActivity)?.originalToolbar()
    }

    override fun categoriesSuccess(categoryResponse: CategoryResponse) {

        val adapter = CategoryAdapter(categoryResponse.categories, this)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.apply {
            homeRecyclerView.adapter = adapter
            homeRecyclerView.layoutManager = layoutManager
        }
    }

    override fun onItemClick(categories: Category) {
        val bundle = Bundle()
        bundle.putParcelable("clickedCategory", categories)

        val subCategoryFragment = SubCategoryFragment()
        subCategoryFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, subCategoryFragment)
            .addToBackStack(null)
            .commit()

        var nameOfCategory = categories.category_name.toString()
        (activity as? MainActivity)?.updateToolbar(nameOfCategory)
    }

    override fun categoriesError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}