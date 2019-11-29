package com.application.bdamx.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.adapter.NewsAdapter
import com.application.bdamx.login.HomeActivity
import com.application.bdamx.model.newsModel
import com.dcwallet.base.BaseFragment
import kotlinx.android.synthetic.main.activity_news.view.*

class NewsFragment   : BaseFragment() {

    var mNewsAdapter: NewsAdapter? = null
    var newsList: MutableList<newsModel> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.activity_news, container, false)


        newsList.add(newsModel("", "", ""))
        newsList.add(newsModel("", "", ""))
        newsList.add(newsModel("", "", ""))
        newsList.add(newsModel("", "", ""))
        newsList.add(newsModel("", "", ""))
        newsList.add(newsModel("", "", ""))
        newsList.add(newsModel("", "", ""))
        newsList.add(newsModel("", "", ""))

        mView!!.news_Recycle.layoutManager =
            LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        mNewsAdapter = NewsAdapter(newsList, activity!!, 1)
        mView!!.news_Recycle.adapter = mNewsAdapter

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).setMenuIcon("News")
    }
}
