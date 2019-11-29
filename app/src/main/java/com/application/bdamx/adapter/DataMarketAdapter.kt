package com.application.bdamx.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.datamarketModel
import kotlinx.android.synthetic.main.adapter_data_market.view.*
import java.text.DecimalFormat

class DataMarketAdapter (var dataList: List<datamarketModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<DataMarketAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_data_market, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(dataList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fourformat = DecimalFormat("#0.0000")
        val twoformat = DecimalFormat("#0.00")
        fun bindItems(marketData: datamarketModel, context: FragmentActivity) {

            println("price=="+marketData.price)

            itemView.mPair.text =marketData.pair
            itemView.mPrice.text =marketData.price
            itemView.mUsdprice.text ="$"+fourformat.format(marketData.usdprice?.toDouble())
            itemView.mChange.text =twoformat.format(marketData.change?.toDouble())+" %"
            itemView.mVol.text ="Vol "+marketData.vol

            var string = marketData.change
            Log.e("string",string)
            var first = string?.substring(0,1).trim()
            Log.e("first==",first)

            if(first!=null&&!first.equals("")&&!first.equals("null")){
                if(first.equals("+")){
                    itemView.mChange.setBackgroundResource(R.drawable.rounded_corner_green)
                }else if(first.equals("-")){
                    itemView.mChange.setBackgroundResource(R.drawable.rounded_corner_red)
                }else{
                    itemView.mChange.setBackgroundResource(R.drawable.rounded_corner_green)
                }
            }


        }
    }
}
