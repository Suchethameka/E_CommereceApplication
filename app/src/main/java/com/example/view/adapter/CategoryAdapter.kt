package com.example.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.databinding.CategoryItemBinding
import com.example.model.remote.dto.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(private val categories: List<Category>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(categories: Category)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])

    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }            fun bind(categories: Category){

            Picasso.get().load(URL_IMAGE +categories.category_image_url).into(binding.imgCategory)
            binding.txtCategory.text = categories.category_name
        }

        override fun onClick(v: View?) {
            itemClickListener.onItemClick(categories[adapterPosition])
        }
    }

    companion object {
        const val URL_IMAGE = "http://10.0.2.2/myshop/images/"
    }
}