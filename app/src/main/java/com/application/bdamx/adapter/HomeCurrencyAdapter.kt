package com.application.bdamx.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.currencyModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_home_currency.view.*
import kotlinx.android.synthetic.main.adapter_home_market.view.*
import java.text.DecimalFormat


class HomeCurrencyAdapter (var walletList: List<currencyModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<HomeCurrencyAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_home_market, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(walletList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val twoformat = DecimalFormat("#0.00")
        fun bindItems(currencyData: currencyModel, context: FragmentActivity) {

            if(currencyData.Img!=null&&!currencyData.Img.equals("")&&!currencyData.Img.equals("null")){
                Picasso.with(context).load(currencyData.Img)
                    .into(itemView.mCurrn_img)
            }

            println("imageee==="+currencyData.Img)
            itemView.mCurrn.text =currencyData.currency
            itemView.mHighhtxt.text =twoformat.format(currencyData.high?.toDouble())
            itemView.mLowtxt.text =twoformat.format(currencyData.low?.toDouble())
            itemView.mOpentxt.text =twoformat.format(currencyData.change?.toDouble())+" %"


        }
    }
}
