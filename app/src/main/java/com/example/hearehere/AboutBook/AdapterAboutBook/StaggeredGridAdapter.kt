package com.example.hearehere.AboutBook.AdapterAboutBook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.hearehere.models.AboutBookData
import com.example.hearehere.R

class StaggeredGridAdapter (private val context: Context,
                            private val categories: MutableList<AboutBookData.Data.Category>) :
    RecyclerView.Adapter<StaggeredGridAdapter.BookStagedViewHolder>() {

    inner class BookStagedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryBut: Button = itemView.findViewById(R.id.book_catogary)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookStagedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories, parent, false)
        return BookStagedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookStagedViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryBut.text = category?.title ?: "Unknown Category"
    }


    override fun getItemCount(): Int {
        return categories.size
    }
}