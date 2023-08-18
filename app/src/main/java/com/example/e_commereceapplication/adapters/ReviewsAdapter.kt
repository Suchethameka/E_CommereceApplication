package com.example.e_commereceapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commereceapplication.databinding.ItemReviewsBinding
import com.example.e_commereceapplication.model.Network.productDetailsModel.Review

class ReviewsAdapter(private val reviewList: List<Any>):
    RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {
    inner class ReviewViewHolder(val binding: ItemReviewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            with(binding) {
                personName.text = review.full_name
                ratingBar.rating = review.rating.toFloat()
                userComment.text = review.review_title
                userDescription.text = review.review
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewsBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount() = reviewList.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }
}