package com.example.hearehere.home.AdapterHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearehere.Model.HomeUserData
import com.example.hearehere.R

class GridBooksAdapter(
    private val context: Context,
    private val books: MutableList<HomeUserData.Data.Data.Book?>?
) :
    RecyclerView.Adapter<GridBooksAdapter.GridBookViewHolder>() {

    class GridBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gridBookCover: ImageView = itemView.findViewById(R.id.grid_book_img)
        val gridBookTitle: TextView = itemView.findViewById(R.id.grid_book_title)
        val GridBookProgressBar: ProgressBar = itemView.findViewById(R.id.grid_book_prograss)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridBookViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.grid_read_book_items, parent, false)
        return GridBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridBookViewHolder, position: Int) {
        val book = books!![position]
        Glide.with(context)
            .load(book?.thumbnail_link)
            .into(holder.gridBookCover)
        holder.gridBookTitle.text = book?.title
        holder.GridBookProgressBar.progress = book?.progress_bar?.toInt() ?: 0
    }

    override fun getItemCount(): Int {
        return books?.size ?: 0
    }
    fun updateListenGrid(newGrid: List<HomeUserData.Data.Data.Book?>) {
        books!!.clear()
        books.addAll(newGrid)
        notifyDataSetChanged()
    }
}
