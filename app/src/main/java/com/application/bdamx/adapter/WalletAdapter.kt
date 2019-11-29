package com.application.bdamx.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.notificationModel
import com.application.bdamx.model.walletModel
import com.application.bdamx.sidemenu.DepositActivity
import com.application.bdamx.sidemenu.WithdrawActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_wallet.view.*
import java.text.DecimalFormat

class WalletAdapter (var walletList: List<walletModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<WalletAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_wallet, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(walletList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cryptoformat = DecimalFormat("#0.00000000")
        fun bindItems(walletData: walletModel, context: FragmentActivity) {

            Picasso.with(context).load(walletData.img)
                .into(itemView.mCoinImg)
            itemView.mCurren.text =walletData.currency
            itemView.mBlnc.text =cryptoformat.format(walletData.blnc?.toDouble())
            //itemView.mBlnc.text =walletData.blnc


            itemView.mDepoBtn.setOnClickListener {
                var I=Intent(context,DepositActivity::class.java)
                I.putExtra("blnc",walletData.blnc)
                I.putExtra("order",walletData.hold)
                I.putExtra("min",walletData.min)
                I.putExtra("max",walletData.max)
                I.putExtra("fee",walletData.fee)
                context.startActivity(I)
            }

            itemView.mWitBtn.setOnClickListener {
                var I=Intent(context,WithdrawActivity::class.java)
                I.putExtra("blnc",walletData.blnc)
                I.putExtra("order",walletData.hold)
                I.putExtra("min",walletData.min)
                I.putExtra("max",walletData.max)
                I.putExtra("fee",walletData.fee)
                context.startActivity(I)
            }


        }
    }
}
