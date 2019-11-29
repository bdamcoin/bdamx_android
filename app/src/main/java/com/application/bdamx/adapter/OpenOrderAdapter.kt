package com.application.bdamx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.datamarketModel
import com.application.bdamx.model.openorderModel

class OpenOrderAdapter (var dataList: List<openorderModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<OpenOrderAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_open_order, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(dataList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(orderData: openorderModel, context: FragmentActivity) {

            //itemView.countryname.text =walletData.name
            //itemView.code.text =walletData.code


        }
    }
}
