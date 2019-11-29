package com.application.bdamx.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.WalletAdapter
import com.application.bdamx.cms.TermsConditionActivity
import com.application.bdamx.login.HomeActivity
import com.application.bdamx.model.walletModel
import com.application.bdamx.utils.DataCallback1
import com.dcwallet.base.BaseFragment
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.activity_wallet.view.*
import kotlinx.android.synthetic.main.activity_wallet.view.notification_recycle
import org.json.JSONObject

class WalletFragment: BaseFragment() {
    var mWalletAdapter : WalletAdapter? = null
    var walletList: MutableList<walletModel> = mutableListOf()
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.activity_wallet, container, false)
        servicerequestCall = ServiceRequestCall(activity!!)
        urlConstants = URLConstants(activity!!)

        mGetWallet()
        return mView
    }

    private fun mGetWallet() {
        walletList.clear()
        val params = HashMap<String, String>()
        try {
            params.put("address",urlConstants!!.tokenaddrs)
        }catch (e:Exception){

        }

        servicerequestCall?.makeStringRequestGet(
            URLConstants.getcurrency,
            activity!!, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("getcurrency-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if(status.equals("true")){
                            val data=Json.optJSONArray("data")
                            for (i in 0..(data.length() - 1)) {
                                var jObj = data.getJSONObject(i)
                                if(jObj.optString("currencySymbol").equals(urlConstants?.nativecurrency)){
                                    walletList.add(walletModel(jObj.optString("currencySymbol"),jObj.optString("balance"),jObj.optString("hold"),jObj.optString("image"),jObj.optString("minwithamt"),jObj.optString("maxwithamt"),jObj.optString("withdrawfee")))
                                }
                            }
                            updateUI(walletList)
                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    fun updateUI(list: List<walletModel>) {
        println("List==" + list.size)

        if (list.size > 0) {
            println("checkk==" + list)
            notification_recycle.layoutManager =
                LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            mWalletAdapter = WalletAdapter(walletList, activity!!, 1)
            notification_recycle.adapter = mWalletAdapter

        } else {
            /* mFound_bank.visibility = View.VISIBLE
             bank_recycle.visibility = View.GONE*/
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).setMenuIcon("Wallet")
    }


}