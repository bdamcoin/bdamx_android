package com.application.bdamx.sidemenu

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.DataMarketAdapter
import com.application.bdamx.adapter.HomeCurrencyAdapter
import com.application.bdamx.adapter.HomeMarketAdapter
import com.application.bdamx.adapter.NewsAdapter
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.model.currencyModel
import com.application.bdamx.model.datamarketModel
import com.application.bdamx.model.newsModel
import com.application.bdamx.utils.DataCallback1
import kotlinx.android.synthetic.main.activity_data_market.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONObject

class DataMarketActivity : BaseActivity() {
    var mMarketAdapter: DataMarketAdapter? = null
    var dataList: MutableList<datamarketModel> = mutableListOf()
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    var mCurrnType: String? = "EOS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_market)
        servicerequestCall = ServiceRequestCall(this@DataMarketActivity)
        urlConstants = URLConstants(this@DataMarketActivity)

        selectedbutton(eos_btn,eth_btn,btc_btn)

        eos_btn.setOnClickListener {
            mCurrnType="EOS"
            selectedbutton(eos_btn,eth_btn,btc_btn)
            mHomepairDetails()
        }

        eth_btn.setOnClickListener {
            mCurrnType="ETH"
            selectedbutton(eth_btn,eos_btn,btc_btn)
            mHomepairDetails()
        }

        btc_btn.setOnClickListener {
            mCurrnType="BTC"
            selectedbutton(btc_btn,eth_btn,eos_btn)
            mHomepairDetails()
        }

        mHomepairDetails()
    }

    fun selectedbutton(viewsel: TextView, viewunsel1: TextView, viewunsel2: TextView) {
        if (this != null) {

            viewsel.setBackgroundResource(R.drawable.bottom_line)
            viewunsel1.setBackgroundResource(0)
            viewunsel2.setBackgroundResource(0)
        }
    }

    private fun mHomepairDetails() {
        dataList.clear()
        val params = HashMap<String, String>()


        servicerequestCall?.makeStringRequestGet(
            URLConstants.homemarket,
            this@DataMarketActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("marketDetails-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if(status.equals("true")){
                            val data=Json.optJSONArray("Message")
                            for (i in 0..(data.length() - 1)) {
                                var cuurnData = data.getJSONObject(i)
                                println("cuurnData=="+cuurnData)
                                var currency = cuurnData.optString("currency")
                                println("currency=="+currency)
                                if (currency.equals(mCurrnType)) {
                                    val pairs = cuurnData.optJSONArray("pairs")
                                    for (i1 in 0..(pairs.length() - 1)) {
                                        var pairsData = pairs.getJSONObject(i1)
                                        println("pairsData=="+pairsData)

                                        var fromCurrency = pairsData.getJSONObject("fromCurrency")
                                        var currencySymbol=fromCurrency.optString("currencySymbol")
                                        var currencyName=fromCurrency.optString("currencyName")
                                        var coinImage=fromCurrency.optString("image")

                                        dataList.add(datamarketModel(pairsData.optString("pair"), pairsData.optString("price"), pairsData.optString("usdprice"), pairsData.optString("change"), pairsData.optString("volume")))

                                    }
                                }

                            }
                            updateUI(dataList)
                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    fun updateUI(list: List<datamarketModel>) {
        println("List==" + list.size)

        if (list.size > 0) {
            println("checkk==" + list)
            datamarket_Recycle.layoutManager =
                LinearLayoutManager(this@DataMarketActivity, RecyclerView.VERTICAL, false)
            mMarketAdapter = DataMarketAdapter(dataList, this@DataMarketActivity, 1)
            datamarket_Recycle.adapter = mMarketAdapter

        } else {
            /* mFound_bank.visibility = View.VISIBLE
             bank_recycle.visibility = View.GONE*/
        }


    }

    override fun onResume() {
        super.onResume()
        updatetoolbar(getString(R.string.datamarket),false)

    }
}
