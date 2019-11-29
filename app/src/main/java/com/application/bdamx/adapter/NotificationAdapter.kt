package com.application.bdamx.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.currencyModel
import com.application.bdamx.model.notificationModel

class NotificationAdapter (var walletList: List<notificationModel>, var context: FragmentActivity, var type: Int, val RegAct: Activity) : RecyclerView.Adapter<NotificationAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_notification, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(walletList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(walletData: notificationModel, context: FragmentActivity) {

            //itemView.countryname.text =walletData.name
            //itemView.code.text =walletData.code


        }
    }
}
