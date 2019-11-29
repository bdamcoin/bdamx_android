package com.application.bdamx.Introslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter


class MyViewPagerAdapter(supportFragmentManager: FragmentManager, layouts: IntArray?, introSlider: IntrosliderActivity) : PagerAdapter() {

    var fragment: Fragment? = null
    var layouts = layouts
    var activity: FragmentActivity? = introSlider
    var inflater: LayoutInflater? = null


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return layouts!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val view: View = inflater!!.inflate(layouts!![position], container, false)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        var view: (View)? = null
        container.removeView(view)
    }


}