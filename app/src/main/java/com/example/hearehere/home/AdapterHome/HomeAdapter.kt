package com.example.hearehere.home.AdapterHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hearehere.Model.HomeUserData
import com.example.hearehere.R

class HomeAdapter(private val context: Context, private val items: HomeUserData):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
  //  private lateinit var binding :HomeAdapter

    companion object{
        private const val firstViewGrid =0
        private const val SecoundViewHeaderHorizantal =1
    }
    inner class firstViewGridHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gridBookCover: ImageView = itemView.findViewById(R.id.grid_book_img)
        val gridBookTitle: TextView = itemView.findViewById(R.id.grid_book_title)
        val GridBookProgressBar: ProgressBar = itemView.findViewById(R.id.grid_book_prograss)
    }
    inner class secoundHorizantalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerText: TextView = itemView.findViewById(R.id.book_parent_tittle_header)
        val headerSeeMore: TextView = itemView.findViewById(R.id.see_more_list_header)
        val horizontalRecyclerView: RecyclerView = itemView.findViewById(R.id.rc_child_horizantal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            firstViewGrid -> {
                val view= LayoutInflater.from(parent.context).inflate(R.layout.grid_read_book_items,parent,false)
                firstViewGridHolder(view)
            }
           else -> {
                val view= LayoutInflater.from(parent.context).inflate(R.layout.parent_rv_book_description,parent,false)
                secoundHorizantalViewHolder(view)
            }
         //   else -> Toast.makeText(requireContext(),"unknown type",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items.data?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            val gridViewHolder = holder as firstViewGridHolder
            val secoundView =holder as secoundHorizantalViewHolder
            holder.headerText.visibility = View.GONE
            holder.headerSeeMore.visibility = View.GONE
//            val gridBooks = items.data?.data?.get(position)?.books?.filterNotNull() ?: listOf()
//            val gridBooksAdapter = GridBooksAdapter(context, gridBooks)
//            gridViewHolder.gridRecyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
//            gridViewHolder.gridRecyclerView.adapter = gridBooksAdapter
  //          gridBooksAdapter.notifyDataSetChanged()
        } else {
            val item = items.data?.data?.get(position)
            val headerHorizontalViewHolder = holder as secoundHorizantalViewHolder
            headerHorizontalViewHolder.headerText.text = item?.title ?: "Title"
            headerHorizontalViewHolder.headerSeeMore.text = "See more"

            val books = item?.books?.filterNotNull() ?: emptyList()
          //  val nestedAdapter = NestedBookAdapter(context, books)
        //    headerHorizontalViewHolder.horizontalRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //    headerHorizontalViewHolder.horizontalRecyclerView.adapter = nestedAdapter
          //  nestedAdapter.notifyDataSetChanged()
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when (position){
           0 -> firstViewGrid
            else -> SecoundViewHeaderHorizantal

        }
    }

}