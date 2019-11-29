package com.application.bdamx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.bookModel
import kotlinx.android.synthetic.main.adapter_order_book.view.*

class OrderBookAdapter (var dataList: List<bookModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<OrderBookAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_order_book, parent, false)

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

            println("type==="+orderData.type)

            if(type==1){
                itemView.mLin_layout.setBackgroundColor(context.resources.getColor(R.color.Colorlistgreen))
                itemView.mPricetxt.setTextColor(context.resources.getColor(R.color.ColorDarkgreen))
            }

            if(type==2){
                itemView.mLin_layout.setBackgroundColor(context.resources.getColor(R.color.Colorlistred))
                itemView.mPricetxt.setTextColor(context.resources.getColor(R.color.cancelRed))
            }

           /* if(orderData.type.equals("buy")){
                itemView.mPricetxt.setTextColor(context.resources.getColor(R.color.ColorDarkgreen))
            }else{
                itemView.mPricetxt.setTextColor(context.resources.getColor(R.color.cancelRed))
            }*/
            //itemView.code.text =walletData.code


        }
    }
}
