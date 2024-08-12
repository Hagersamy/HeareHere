package com.example.hearehere.home.adapter_home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearehere.models.HomeUserData
import com.example.hearehere.R

class NestedBookAdapter(private val context: Context, private val books: MutableList<HomeUserData.Data.Data.Book>,
                        private val clickListener: (HomeUserData.Data.Data.Book) -> Unit):
    RecyclerView.Adapter<NestedBookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val headerBookCover: ImageView =itemView.findViewById(R.id.header_cover)
        val headerBookTitle: TextView =itemView.findViewById(R.id.header_book_title)
        val headerBookAuthor: TextView =itemView.findViewById(R.id.header_book_author)
    }
///// something wrong
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.child_rv_book_contant,parent,false)
       return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
       return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book =books[position]
        holder.headerBookTitle.text=book.title
        holder.headerBookAuthor.text=book.author_name

        Glide.with(holder.itemView.context)
            .load(book.thumbnail_link)
            .into(holder.headerBookCover)
        holder.headerBookCover.setOnClickListener {
            clickListener(book)
        }
    }
    fun updateBooks(newBooks: List<HomeUserData.Data.Data.Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }


}