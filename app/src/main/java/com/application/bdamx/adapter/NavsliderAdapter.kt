package com.application.bdamx.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.model.currencyModel
import com.application.bdamx.model.navigationModel
import kotlinx.android.synthetic.main.adapter_home_currency.view.*
import kotlinx.android.synthetic.main.adapter_nav_slider.view.*

class NavsliderAdapter(var activity: FragmentActivity, var type:Int, var menulist: MutableList<navigationModel>, @NonNull OnItemClickListener1:OnItemClickListener): RecyclerView.Adapter<NavsliderAdapter.viewHolder>() {
    lateinit var onItemClick: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(item: navigationModel)
    }

    init {
        this.onItemClick = OnItemClickListener1;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_nav_slider, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return menulist.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bindItems(menulist[position], activity)

    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(navData: navigationModel, context: FragmentActivity) {

            itemView.mSliderimg.setImageResource(navData.img)
            itemView.mSlidertitle.text=navData.name
            itemView.setOnClickListener {
                onItemClick.onItemClick(navData)
            }


        }
    }
}
