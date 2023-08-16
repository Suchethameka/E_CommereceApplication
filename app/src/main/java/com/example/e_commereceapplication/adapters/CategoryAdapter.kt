package com.example.e_commereceapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.databinding.CategoriesItemBinding
import com.example.e_commereceapplication.model.Network.VolleyConstants
import com.example.e_commereceapplication.model.Network.category.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(val categoryList:List<Category>, val itemClickListener: ItemClickListener):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: CategoriesItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            with(binding){
                tvCategoryName.text = category.category_name
                val image = "${VolleyConstants.IMAGE_URL}${category.category_image_url}"
                Picasso.get().load(image).into(categoryImage)
                binding.root.setOnClickListener {
                    itemClickListener.isSelected(category.category_id)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoriesItemBinding.inflate(layoutInflater)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }
}