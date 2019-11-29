package com.application.bdamx.login.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.HomeCurrencyAdapter
import com.application.bdamx.adapter.HomeMarketAdapter
import com.application.bdamx.login.HomeActivity
import com.application.bdamx.model.currencyModel
import com.application.bdamx.model.newsModel
import com.application.bdamx.utils.DataCallback1
import com.dcwallet.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.currency_recycle
import kotlinx.android.synthetic.main.fragment_portfolio.view.*
import org.json.JSONException
import org.json.JSONObject

class HomeFragment : BaseFragment() {

    var mMarketAdapter : HomeMarketAdapter? = null
    var mCurrencyAdapter : HomeCurrencyAdapter? = null
    var marketList: MutableList<currencyModel> = mutableListOf()
    var currencyList: MutableList<currencyModel> = mutableListOf()
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    var mCurrnType: String? = "EOS"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        servicerequestCall = ServiceRequestCall(activity!!)
        urlConstants = URLConstants(activity!!)


        println("currencytype=="+urlConstants?.nativecurrency)

        selectedbutton(mView!!.eos_btn,mView!!.eth_btn,mView!!.btc_btn)

        mView!!.eos_btn.setOnClickListener {
            mCurrnType="EOS"
            selectedbutton(mView!!.eos_btn,mView!!.eth_btn,mView!!.btc_btn)
            mHomepairDetails()
        }

        mView!!.eth_btn.setOnClickListener {
            mCurrnType="ETH"
            selectedbutton(mView!!.eth_btn,mView!!.eos_btn,mView!!.btc_btn)
            mHomepairDetails()
        }

        mView!!.btc_btn.setOnClickListener {
            mCurrnType="BTC"
            selectedbutton(mView!!.btc_btn,mView!!.eth_btn,mView!!.eos_btn)
            mHomepairDetails()
        }
        mHomepairDetails()
        mMarketDetails()

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun selectedbutton(viewsel: TextView, viewunsel1: TextView, viewunsel2: TextView) {
        if (this != null) {

            viewsel.setBackgroundResource(R.drawable.bottom_line)
            viewunsel1.setBackgroundResource(0)
            viewunsel2.setBackgroundResource(0)
        }
    }


    private fun mHomepairDetails() {
        marketList.clear()

        val params = HashMap<String, String>()
        servicerequestCall?.makeStringRequestGet(
            URLConstants.homemarket,
            activity!!, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("homepairDetails-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if(status.equals("true")){
                            val data=Json.optJSONArray("Message")
                            for (i in 0..(data.length() - 1)) {
                                var cuurnData = data.getJSONObject(i)
                                var currency = cuurnData.optString("currency")
                                if (currency.equals(mCurrnType)) {
                                    val pairs = cuurnData.optJSONArray("pairs")
                                    for (i1 in 0..(pairs.length() - 1)) {
                                        var pairsData = pairs.getJSONObject(i1)
                                        println("pairsData=="+pairsData)

                                        var fromCurrency = pairsData.getJSONObject("fromCurrency")
                                        var currencySymbol=fromCurrency.optString("currencySymbol")
                                        var currencyName=fromCurrency.optString("currencyName")
                                        var coinImage=fromCurrency.optString("image")

                                        marketList.add(currencyModel(coinImage,currencySymbol,currencyName,pairsData.optString("high"),pairsData.optString("low"),pairsData.optString("change"),pairsData.optString("marketPrice"),pairsData.optString("pair")))
                                        println("marketttt=="+marketList.size)
                                    }
                                }

                            }
                            println("marketsize=="+marketList.size)
                            mView!!.currency_recycle.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
                            mCurrencyAdapter = HomeCurrencyAdapter(marketList, activity!!, 1)
                            mView!!.currency_recycle.adapter = mCurrencyAdapter

                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
    fun updateUI(list: List<currencyModel>) {
        println("payList==" + list.size)

        if (list.size > 0) {
            println("checkk==" + list)
            mView!!.currency_recycle.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            mCurrencyAdapter = HomeCurrencyAdapter(list, activity!!, 1)
            mView!!.currency_recycle.adapter = mCurrencyAdapter


        } else {
            /* mFound_bank.visibility = View.VISIBLE
             bank_recycle.visibility = View.GONE*/
        }


    }

    private fun mMarketDetails() {
        currencyList.clear()
        val params = HashMap<String, String>()

        servicerequestCall?.makeStringRequestGet(
            URLConstants.homemarket,
            activity!!, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("homepairDetails-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if(status.equals("true")){
                            val data=Json.optJSONArray("Message")
                            for (i in 0..(data.length() - 1)) {
                                var cuurnData = data.getJSONObject(i)
                                var currency = cuurnData.optString("currency")
                               // if (currency.equals(mCurrnType)) {
                                    val pairs = cuurnData.optJSONArray("pairs")
                                    for (i1 in 0..(pairs.length() - 1)) {
                                        var pairsData = pairs.getJSONObject(i1)
                                        println("pairsData=="+pairsData)

                                        var fromCurrency = pairsData.getJSONObject("fromCurrency")
                                        var currencySymbol=fromCurrency.optString("currencySymbol")
                                        var currencyName=fromCurrency.optString("currencyName")
                                        var coinImage=fromCurrency.optString("image")

                                        currencyList.add(currencyModel(coinImage,currencySymbol,currencyName,pairsData.optString("high"),pairsData.optString("low"),pairsData.optString("change"),pairsData.optString("marketPrice"),pairsData.optString("pair")))
                                        println("marketttt=="+marketList.size)
                                    }
                               // }

                            }
                            println("currencyListsize=="+currencyList.size)

                            mView!!.market_recycle.layoutManager = LinearLayoutManager(activity!!, RecyclerView.HORIZONTAL, false)
                            mMarketAdapter = HomeMarketAdapter(currencyList, activity!!, 1)
                            mView!!.market_recycle.adapter = mMarketAdapter
                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }




    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).setMenuIcon("Exchange Trading")
    }


}