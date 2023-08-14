package com.example.e_commereceapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.adapters.CategoryAdapter
import com.example.e_commereceapplication.data.Category
import com.example.e_commereceapplication.data.CategoryResponse
import com.example.e_commereceapplication.databinding.ActivityMainBinding
import com.example.e_commereceapplication.model.VolleyHandler
import com.example.e_commereceapplication.presenter.CategoryPresenter
import com.example.e_commereceapplication.presenter.MVPCategory


class MainActivity : AppCompatActivity(), MVPCategory.CategoryView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MVPCategory.ICategoryPresenter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CategoryPresenter(this, VolleyHandler(this))
        val categoryItemClickListener = object : CategoryAdapter.OnCategoryItemClickListener {
            override fun onItemClick(category: Category) {
                // Handle category item click here
            }
        }

        categoryAdapter = CategoryAdapter(categoryItemClickListener)


        binding.recyclerViewCategories.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = categoryAdapter
        }

        presenter.fetchCategories()
    }


    override fun showCategoryList(categories: List<Category>) {
        categoryAdapter.setCategories(categories)
    }

    override fun showCategoryFetchError(error: String) {
        Toast.makeText(this, "Error fetching categories: $error", Toast.LENGTH_SHORT).show()
    }
}
