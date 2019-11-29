package com.application.bdamx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.openorderModel
import com.application.bdamx.model.orderModel

class OrderHistoryAdapter  (var dataList: List<orderModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<OrderHistoryAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_order_history, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(dataList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(orderData: orderModel, context: FragmentActivity) {

            //itemView.countryname.text =walletData.name
            //itemView.code.text =walletData.code


        }
    }
}
