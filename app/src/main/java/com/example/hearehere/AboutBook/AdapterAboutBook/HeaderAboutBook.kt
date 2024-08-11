package com.example.hearehere.AboutBook.AdapterAboutBook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hearehere.Model.AboutBookData
import com.example.hearehere.R

class HeaderAboutBook(private val context: Context,
                      private val books: MutableList<AboutBookData>) :
    RecyclerView.Adapter<HeaderAboutBook.HeaderBookViewHolder>() {

    inner class HeaderBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerText: TextView = itemView.findViewById(R.id.book_parent_tittle_header)
        val headerSeeMore: TextView = itemView.findViewById(R.id.see_more_list_header)
        val horizontalRecyclerView: RecyclerView = itemView.findViewById(R.id.rc_child_horizantal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_rv_book_description, parent, false)
        return HeaderBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }
    override fun onBindViewHolder(holder: HeaderBookViewHolder, position: Int) {
        val book = books[position]
        holder.headerText.text = book.data?.title
        holder.headerSeeMore.text ="See more"

        val categories = book.data?.categories?.filterNotNull() ?: emptyList()
        val nestedAdapter = AboutBooksAdapter(context,  categories.toMutableList())
        holder.horizontalRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.horizontalRecyclerView.adapter = nestedAdapter
    }




}