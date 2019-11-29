package com.application.bdamx.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.model.currencyModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_home_currency.view.*
import java.text.DecimalFormat


class HomeMarketAdapter (var walletList: List<currencyModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<HomeMarketAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_home_currency, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(walletList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var urlConstants: URLConstants? = null
        val cryptoformat = DecimalFormat("#0.00000000")
        fun bindItems(marketData: currencyModel, context: FragmentActivity) {
            urlConstants = URLConstants(context)
            if(marketData.Img!=null&&!marketData.Img.equals("")&&!marketData.Img.equals("null")){
                Picasso.with(context).load(marketData.Img)
                    .into(itemView.currn_img)
            }

            itemView.mCurrnname.text =marketData.pairname


            val separated = marketData.pair?.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var pairOne= separated[0] // this will contain "Fruit"
            var pairTwo= separated[1]

            itemView.mMarkettxt.text ="1"+pairOne+"="+cryptoformat.format(marketData.marketPrice?.toDouble())+pairTwo






        }
    }
}
