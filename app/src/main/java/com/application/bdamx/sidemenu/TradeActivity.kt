package com.application.bdamx.sidemenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.DataMarketAdapter
import com.application.bdamx.adapter.OpenOrderAdapter
import com.application.bdamx.adapter.OrderHistoryAdapter
import com.application.bdamx.model.datamarketModel
import com.application.bdamx.model.openorderModel
import com.application.bdamx.model.orderModel
import com.application.bdamx.model.tradepairModel
import com.application.bdamx.utils.DataCallback1
import com.application.bdamx.utils.MyApplication
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_trade.*
import kotlinx.android.synthetic.main.trade_toolbar.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


class TradeActivity : AppCompatActivity() {
    var mOpenorderAdapter: OpenOrderAdapter? = null
    var openorderList: MutableList<openorderModel> = mutableListOf()
    var mOrderhistoryAdapter: OrderHistoryAdapter? = null
    var orderList: MutableList<orderModel> = mutableListOf()
    private var spinnertypeArray: ArrayAdapter<String>? = null
    var pairList: ArrayList<String>? = arrayListOf()
    var tradepairList: MutableList<tradepairModel> = mutableListOf()
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    private var mSocket: Socket? = null
    private var mPairID: String? = null
    private var mFromID: String? = null
    private var mToID: String? = null
    private var mMarketprice: String? = null
    private var isConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade)
        servicerequestCall = ServiceRequestCall(this@TradeActivity)
        urlConstants = URLConstants(this@TradeActivity)
        fav_img.visibility= View.GONE

        initivariable()
        mGetpair()

        selectedbutton(openorder_btn,orderhistory_btn,ordercancel_btn)

        openorder_btn.setOnClickListener {
            selectedbutton(openorder_btn,orderhistory_btn,ordercancel_btn)

            tradeRecycle.layoutManager = LinearLayoutManager(this@TradeActivity, RecyclerView.VERTICAL, false)
            mOpenorderAdapter = OpenOrderAdapter(openorderList, this@TradeActivity, 1)
            tradeRecycle.adapter = mOpenorderAdapter
        }

        orderhistory_btn.setOnClickListener {
            selectedbutton(orderhistory_btn,openorder_btn,ordercancel_btn)

            tradeRecycle.layoutManager = LinearLayoutManager(this@TradeActivity, RecyclerView.VERTICAL, false)
            mOrderhistoryAdapter = OrderHistoryAdapter(orderList, this@TradeActivity, 1)
            tradeRecycle.adapter = mOrderhistoryAdapter
        }

        ordercancel_btn.setOnClickListener {
            selectedbutton(ordercancel_btn,openorder_btn,orderhistory_btn)
        }

        openorderList.add(openorderModel("", "", "","","","",""))
        openorderList.add(openorderModel("", "", "","","","",""))
        openorderList.add(openorderModel("", "", "","","","",""))
        openorderList.add(openorderModel("", "", "","","","",""))
        openorderList.add(openorderModel("", "", "","","","",""))
        openorderList.add(openorderModel("", "", "","","","",""))

        tradeRecycle.layoutManager = LinearLayoutManager(this@TradeActivity, RecyclerView.VERTICAL, false)
        mOpenorderAdapter = OpenOrderAdapter(openorderList, this@TradeActivity, 1)
        tradeRecycle.adapter = mOpenorderAdapter

        orderList.add(orderModel("", "", "","","","","",""))
        orderList.add(orderModel("", "", "","","","","",""))
        orderList.add(orderModel("", "", "","","","","",""))
        orderList.add(orderModel("", "", "","","","","",""))
        orderList.add(orderModel("", "", "","","","","",""))
        orderList.add(orderModel("", "", "","","","","",""))


        mBuybtn.setOnClickListener {
            var I=Intent(this@TradeActivity,TradeCommonActivity::class.java)
            startActivity(I)
        }

        mBuybtn.setOnClickListener {
            var I=Intent(this@TradeActivity,TradeCommonActivity::class.java)
            startActivity(I)
        }

        mTradespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                makePairDetails()
                //mEmituserResponhistory()
            }
        }

        mGetCancelledorder()
    }

    fun initivariable(){

        var app = MyApplication()
        mSocket=app.socket
        mSocket!!.on(Socket.EVENT_CONNECT, onConnect)
        mSocket!!.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket!!.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket!!.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)
        mSocket!!.on("emitPairsResponse", onNewMessage)
        mSocket!!.on("userResponse", onUserMessage)
        //mSocket!!.on("createResponse", onCreatereposne)
        mSocket!!.on(Socket.EVENT_ERROR, onError)
        mSocket!!.connect()
    }

    fun selectedbutton(viewsel: TextView, viewunsel1: TextView, viewunsel2: TextView) {
        if (this != null) {

            viewsel.setBackgroundResource(R.drawable.bottom_line)
            viewunsel1.setBackgroundResource(0)
            viewunsel2.setBackgroundResource(0)
        }
    }

    private fun mGetpair() {
        pairList!!.clear()
        tradepairList!!.clear()
        val params = HashMap<String, String>()


        servicerequestCall?.makeStringRequestGet(
            URLConstants.homemarket,
            this@TradeActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("mGetpair-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        status = Json.optString("status")
                        if(status.equals("true")){
                            val data=Json.optJSONArray("Message")
                            for (i in 0..(data.length() - 1)) {
                                var cuurnData = data.getJSONObject(i)
                                println("cuurnData=="+cuurnData)
                                var currency = cuurnData.optString("currency")
                                println("currency=="+currency)
                                    val pairs = cuurnData.optJSONArray("pairs")
                                    for (i1 in 0..(pairs.length() - 1)) {
                                        var pairsData = pairs.getJSONObject(i1)
                                        println("pairsData=="+pairsData)
                                        pairList!!.add(pairsData.optString("pair"))
                                        var fromCurrency = pairsData.getJSONObject("fromCurrency")
                                        var toCurrency = pairsData.getJSONObject("toCurrency")
                                        tradepairList.add(tradepairModel(pairsData.optString("makerFee"),pairsData.optString("takerFee"),pairsData.optString("marketPrice"),pairsData.optString("pair"), pairsData.optString("_id"), fromCurrency.optString("_id"), toCurrency.optString("_id")))

                                    }
                            }
                            spinnertypeArray = ArrayAdapter(this@TradeActivity, R.layout.spinner_item, pairList!!)
                            spinnertypeArray!!.setDropDownViewResource(R.layout.spinner_dropdown)
                            mTradespinner.setAdapter(spinnertypeArray)
                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    fun makePairDetails() {
        if (tradepairList.size != 0) {
            for (i in tradepairList.indices) {
                if (tradepairList.get(i).pair.equals(mTradespinner.selectedItem.toString())) {
                    println("idd---" + tradepairList.get(i).pair)
                    mPairID=tradepairList.get(i).pairid
                    mFromID=tradepairList.get(i).fromid
                    mToID=tradepairList.get(i).toid
                    mMarketprice=tradepairList.get(i).marketPrice

                } else {
                }
            }
        }
    }


    private fun mGetCancelledorder() {
        //pairList!!.clear()
        //tradepairList!!.clear()
        val params = HashMap<String, String>()
        try {
            params.put("userId", "2NAb5pStEZ8AJ4UtWT9AVhFmfwmPwJaw5ic")
            params.put("pairid", "5ca3310f7e426d406a8f56d5")


        } catch (e: JSONException) {
            Log.e("error" , e.toString())
        }

        servicerequestCall?.makeStringRequestGet(
            URLConstants.cancelorder,
            this@TradeActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("cancelorder-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        status = Json.optString("status")
                        if(status.equals("true")){
                           // val data=Json.optJSONArray("Message")
                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }



    //Socket connection

    private val onConnect = Emitter.Listener {
        runOnUiThread(Runnable {

            if (isConnected) {
                Toast.makeText(this,"Connected", Toast.LENGTH_LONG).show()
            }

            mEmituserResponhistory()
        })
    }

    private val onDisconnect = Emitter.Listener {
        runOnUiThread(Runnable {
            isConnected = false
            Toast.makeText(this,"Disconnected", Toast.LENGTH_LONG).show()
        })
    }

    private val onConnectError = Emitter.Listener {
        runOnUiThread(Runnable {

            Toast.makeText(this,"Failed to Connect", Toast.LENGTH_LONG
            ).show()
        })
    }


    private val onNewMessage = object: Emitter.Listener {
        override fun call(vararg args:Any) {
            Log.e("Newmessage", "Newmessagecalled")
            val data = args[0] as JSONObject
            Log.e("data", "" + data)
            runOnUiThread(object:Runnable {
                public override fun run() {
                    Log.e("Newmessage", "Newmessagecalled")
                    val data = args[0] as JSONObject
                    Log.e("data", "" + data)

                }
            })
        }
    }

    private val onUserMessage = object: Emitter.Listener {
        override fun call(vararg args:Any) {
            Log.e("onUserMessage", "Newmessagecalled")
            val data = args[0] as JSONObject
            Log.e("historydata", "" + data)
            runOnUiThread(object:Runnable {
                public override fun run() {
                    Log.e("onUserMessage", "Newmessagecalled")
                    val data = args[0] as JSONObject
                    Log.e("UserMessagedata", "" + data)

                }
            })
        }
    }

    private val onCreatereposne = object: Emitter.Listener {
        override fun call(vararg args:Any) {
            Log.e("onCreateMessage", "Newmessagecalled")
            val data = args[0] as JSONObject
            Log.e("Createdata", "" + data)
            runOnUiThread(object:Runnable {
                public override fun run() {
                    Log.e("onCreateMessage", "Newmessagecalled")
                    val data = args[0] as JSONObject
                    Log.e("CreateMessagedata", "" + data)

                }
            })
        }
    }


    private val onError = Emitter.Listener { args ->
        Log.e("data", "" + args[0])
    }

    //emit param for user response history
    fun mEmituserResponhistory(){
        val obj = JSONObject()
        try {
           /* obj.put("userid", urlConstants!!.tokenaddrs)
            obj.put("pairid", mPairID);
            obj.put("fromcurn", mFromID);
            obj.put("tocurn", mToID);*/

            obj.put("userid", "2MwzKRU8iLSPRPmr9TrohsXgUcu8CMtfYUF")
            obj.put("pairid", "5dbfc16d0960b8233b740287");
            obj.put("fromcurn", "5dbfc16d0960b8233b740285");
            obj.put("tocurn", "5ca32fe67e426d406a8f56d4");

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val message = obj.toString()
        Log.e("Param=", message)
        mSocket?.emit("userResponse", message);
    }

}
