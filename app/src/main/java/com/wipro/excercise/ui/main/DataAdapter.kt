package com.wipro.excercise.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wipro.excercise.R
import com.wipro.excercise.data.DataItem
import kotlinx.android.synthetic.main.listitem_main.view.*

class DataAdapter : RecyclerView.Adapter<DataAdapter.ItemView>() {

    private var listData: MutableList<DataItem> = mutableListOf()

    private val picasso = Picasso.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_main, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(listData[position])
    }

    fun updateList(list: List<DataItem>) {
        listData.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ItemView(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(dataItem: DataItem) {
            view.listItem_tv_title.text = dataItem.title
            view.listItem_tv_detail.text = dataItem.description
            picasso.load(dataItem.imageHref)
                .placeholder(R.drawable.ic_sync_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(view.listItem_iv)
        }
    }
}
