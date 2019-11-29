package com.application.bdamx.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.adapter.HomeCurrencyAdapter
import com.application.bdamx.adapter.HomeMarketAdapter
import com.application.bdamx.adapter.PortfolioAdapter
import com.application.bdamx.login.HomeActivity
import com.application.bdamx.model.currencyModel
import com.dcwallet.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_portfolio.view.*


class PortfolioFragment  : BaseFragment() {

    var mPortfolioAdapter : PortfolioAdapter? = null
    var mMainAdapter : HomeMarketAdapter? = null
    var marketList: MutableList<currencyModel> = mutableListOf()
    var currencyList: MutableList<currencyModel> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_portfolio, container, false)


       /* marketList.add(currencyModel(R.drawable.btc,"",""))
        marketList.add(currencyModel(R.drawable.eth,"",""))
        marketList.add(currencyModel(R.drawable.btc_blue,"",""))
        marketList.add(currencyModel(R.drawable.eth,"",""))
        marketList.add(currencyModel(R.drawable.btc,"",""))
        marketList.add(currencyModel(R.drawable.eth,"",""))
        marketList.add(currencyModel(R.drawable.btc,"",""))
        marketList.add(currencyModel(R.drawable.eth,"",""))*/

        mView!!.main_recycle.layoutManager = LinearLayoutManager(activity!!, RecyclerView.HORIZONTAL, false)
        mMainAdapter = HomeMarketAdapter(marketList, activity!!, 1)
        mView!!.main_recycle.adapter = mMainAdapter


        mView!!.portfolio_recycle.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        mPortfolioAdapter = PortfolioAdapter(marketList, activity!!, 1,activity!!)
        mView!!.portfolio_recycle.adapter = mPortfolioAdapter





        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).setMenuIcon("Portfolio")
    }


}