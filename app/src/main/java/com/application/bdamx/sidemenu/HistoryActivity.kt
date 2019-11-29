package com.application.bdamx.sidemenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.adapter.DepositHistoryAdapter
import com.application.bdamx.adapter.LatestTransactionAdapter
import com.application.bdamx.adapter.OrderBookAdapter
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.model.bookModel
import com.application.bdamx.model.historyModel
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_trade_common.*

class HistoryActivity : BaseActivity() {
    var mDepoAdapter: DepositHistoryAdapter? = null
    var depositList: MutableList<historyModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        depositList.add(historyModel("","", "", "","","","",""))
        depositList.add(historyModel("","", "", "","","","",""))
        depositList.add(historyModel("","", "", "","","","",""))
        depositList.add(historyModel("","", "", "","","","",""))
        depositList.add(historyModel("","", "", "","","","",""))
        depositList.add(historyModel("","", "", "","","","",""))


        historyRecycle.layoutManager = LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL, false)
        mDepoAdapter = DepositHistoryAdapter(depositList, this@HistoryActivity, 1)
        historyRecycle.adapter = mDepoAdapter

        initvariable()
    }

    fun initvariable(){

        selectedbutton(depo_btn,withdraw_btn)



        depo_btn.setOnClickListener {
            selectedbutton(depo_btn, withdraw_btn)

            historyRecycle.layoutManager = LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL, false)
            mDepoAdapter = DepositHistoryAdapter(depositList, this@HistoryActivity, 1)
            historyRecycle.adapter = mDepoAdapter
        }

        withdraw_btn.setOnClickListener {
            selectedbutton(withdraw_btn, depo_btn)

            historyRecycle.layoutManager = LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL, false)
            mDepoAdapter = DepositHistoryAdapter(depositList, this@HistoryActivity, 1)
            historyRecycle.adapter = mDepoAdapter
        }
    }

    fun selectedbutton(viewsel: TextView, viewunsel1: TextView) {
        if (this != null) {
            viewsel.setBackgroundResource(R.drawable.bottom_line)
            viewunsel1.setBackgroundResource(0)
        }
    }

    override fun onResume() {
        super.onResume()
        updatetoolbar(getString(R.string.history),false)

    }
}
