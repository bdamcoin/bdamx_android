package com.application.bdamx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.bookModel
import kotlinx.android.synthetic.main.adapter_order_book.view.*

class LatestTransactionAdapter  (var dataList: List<bookModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<LatestTransactionAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_latest_transcation, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(dataList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(orderData: bookModel, context: FragmentActivity) {

            itemView.mPricetxt.text =orderData.date


        }
    }
}
