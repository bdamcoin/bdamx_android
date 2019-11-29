package com.application.bdamx.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.cms.NewsDetailActivity
import com.application.bdamx.model.currencyModel
import com.application.bdamx.model.newsModel
import kotlinx.android.synthetic.main.adapter_news.view.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter (var walletList: List<newsModel>, var context: FragmentActivity, var type: Int) : RecyclerView.Adapter<NewsAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_news, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(walletList[position], context)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(newzData: newsModel, context: FragmentActivity) {

            itemView.newsTitle.text =newzData.title
            itemView.content.text =newzData.content

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = sdf.parse(newzData.date)
            val cal = Calendar.getInstance()
            cal.time = date

            itemView.monthYear.text= DateFormatSymbols.getInstance().shortMonths.get(cal.get(Calendar.MONTH)).toString()+" "+cal.get(
                Calendar.YEAR).toString()

            itemView.date.text=cal.get(Calendar.DATE).toString()

            itemView.cardView4.setOnClickListener {
                var I=Intent(context,NewsDetailActivity::class.java)
                I.putExtra("title",newzData.title)
                I.putExtra("desc",newzData.content)
                context.startActivity(I)
            }

        }
    }
}
