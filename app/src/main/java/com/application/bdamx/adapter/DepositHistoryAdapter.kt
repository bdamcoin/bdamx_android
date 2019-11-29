package com.application.bdamx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.historyModel

class DepositHistoryAdapter (var walletList: List<historyModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<DepositHistoryAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_deposit, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(walletList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(walletData: historyModel, context: FragmentActivity) {


            //itemView.code.text =walletData.code

        }
    }
}
