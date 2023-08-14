package com.example.e_commereceapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.data.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(private val itemClickListener: OnCategoryItemClickListener) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val categories: MutableList<Category> = mutableListOf()

    fun setCategories(categories: List<Category>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {
            itemView.findViewById<TextView>(R.id.text_category_name).text = category.category_name
            val imageUrl = BASE_IMAGE_URL + category.category_image_url
            Picasso.get()
                .load(imageUrl)
                .into(itemView.findViewById(R.id.image_category) as ImageView)
            itemView.setOnClickListener {
                itemClickListener.onItemClick(category)
            }
        }
    }

    interface OnCategoryItemClickListener {
        fun onItemClick(category: Category)
    }

    companion object {
        private const val BASE_IMAGE_URL = "http://10.0.2.2/myshop/images/Category/"
    }
}
