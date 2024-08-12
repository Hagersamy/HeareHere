package com.example.hearehere.home.adapter_home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hearehere.models.HomeUserData
import com.example.hearehere.R

class AdapterHome1(private val context: Context, private var items:HomeUserData,
                   private val clickListener: (HomeUserData.Data.Data.Book) -> Unit) :
    RecyclerView.Adapter<AdapterHome1.CommonViewHolder>() {

    companion object {
        private const val VIEW_TYPE_FIRST = 0
        private const val VIEW_TYPE_OTHER = 1
    }

    inner class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerText: TextView = itemView.findViewById(R.id.book_parent_tittle_header)
        val headerSeeMore: TextView = itemView.findViewById(R.id.see_more_list_header)
        val horizontalRecyclerView: RecyclerView = itemView.findViewById(R.id.rc_child_horizantal)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_rv_book_description, parent, false)
        return CommonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.data?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val item = items.data?.data?.get(position)



        if (position == 0) {
            holder.headerText.visibility = View.GONE
            holder.headerSeeMore.visibility = View.GONE

            val books = item?.books?.toMutableList() ?: mutableListOf()
            val nestedAdapter = GridBooksAdapter(context, books)
            holder.horizontalRecyclerView.layoutManager = GridLayoutManager(context,2,
                GridLayoutManager.HORIZONTAL,false)
            holder.horizontalRecyclerView.adapter = nestedAdapter

        } else {
            holder.headerText.visibility = View.VISIBLE
            holder.headerSeeMore.visibility = View.VISIBLE
            holder.headerText.text = item?.title ?: "Title"
            holder.headerSeeMore.text = "See more"
            val books = item?.books?.filterNotNull()?.toMutableList() ?: mutableListOf()
            val nestedAdapter = NestedBookAdapter(context, books,clickListener)
            holder.horizontalRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            holder.horizontalRecyclerView.adapter = nestedAdapter
        }

      //  }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_FIRST
            else -> VIEW_TYPE_OTHER
        }
    }



}
