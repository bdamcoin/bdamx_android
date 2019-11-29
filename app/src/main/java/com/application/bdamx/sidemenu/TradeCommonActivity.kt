package com.application.bdamx.sidemenu

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.LatestTransactionAdapter
import com.application.bdamx.adapter.OpenOrderAdapter
import com.application.bdamx.adapter.OrderBookAdapter
import com.application.bdamx.model.bookModel
import com.application.bdamx.model.openorderModel
import com.application.bdamx.model.tradepairModel
import com.application.bdamx.utils.DataCallback1
import com.application.bdamx.utils.MyApplication
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_trade_common.*
import kotlinx.android.synthetic.main.activity_trade_common.book_btn
import kotlinx.android.synthetic.main.activity_trade_common.mOrdertype_layout
import kotlinx.android.synthetic.main.activity_trade_common.mRow1
import kotlinx.android.synthetic.main.activity_trade_common.mRow2
import kotlinx.android.synthetic.main.activity_trade_common.mRow3
import kotlinx.android.synthetic.main.activity_trade_common.myhistory_btn
import kotlinx.android.synthetic.main.activity_trade_common.tradeRecycle
import kotlinx.android.synthetic.main.activity_tradee_common.*
import kotlinx.android.synthetic.main.popup_layout.*
import kotlinx.android.synthetic.main.trade_toolbar.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class TradeCommonActivity : AppCompatActivity() {
    var mBookAdapter: OrderBookAdapter? = null
    var bookorderList: MutableList<bookModel> = mutableListOf()
    var mLasttransactionAdapter: LatestTransactionAdapter? = null
    var transactionList: MutableList<bookModel> = mutableListOf()
    private var spinnertypeArray: ArrayAdapter<String>? = null
    var pairList: ArrayList<String>? = arrayListOf()
    var tradepairList: MutableList<tradepairModel> = mutableListOf()
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    private var mPairID: String? = null
    private var mFromID: String? = null
    private var mToID: String? = null
    private var mMarketprice: String? = null
    private var mMaker: String? = null
    private var mTaker: String? = null
    private var mTradeType: String? = "buy"
    private var mOrderType: String? = "limit"
    private var mSocket: Socket? = null
    private var isConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tradee_common)
        servicerequestCall = ServiceRequestCall(this@TradeCommonActivity)
        urlConstants = URLConstants(this@TradeCommonActivity)

        mGetpair()
        initvariable()


        fav_img.setOnClickListener {
            var I=Intent(this@TradeCommonActivity,TradeActivity::class.java)
            startActivity(I)
        }

        mOrdertype_layout.setOnClickListener {
            mOrderpopup()
        }

        bookorderList.add(bookModel("","", "", "","","buy"))
        bookorderList.add(bookModel("","", "", "","","buy"))
        bookorderList.add(bookModel("","", "", "","","sell"))
        bookorderList.add(bookModel("","", "", "","","buy"))
        bookorderList.add(bookModel("","", "", "","","sell"))
        bookorderList.add(bookModel("","", "", "","","buy"))

        transactionList.add(bookModel("02/02/2017 03.57.01","", "", "","","buy"))
        transactionList.add(bookModel("02/02/2017 03.57.01","", "", "","","buy"))
        transactionList.add(bookModel("02/02/2017 03.57.01","", "", "","","buy"))
        transactionList.add(bookModel("02/02/2017 03.57.01","", "", "","","buy"))
        transactionList.add(bookModel("02/02/2017 03.57.01","", "", "","","buy"))
        transactionList.add(bookModel("02/02/2017 03.57.01","", "", "","","buy"))

        /*tradeRecycle.layoutManager = LinearLayoutManager(this@TradeCommonActivity, RecyclerView.VERTICAL, false)
        mBookAdapter = OrderBookAdapter(transactionList, this@TradeCommonActivity, 1)
        tradeRecycle.adapter = mBookAdapter*/


        buy_recycle.layoutManager = LinearLayoutManager(this@TradeCommonActivity, RecyclerView.VERTICAL, false)
        mBookAdapter = OrderBookAdapter(transactionList, this@TradeCommonActivity, 1)
        buy_recycle.adapter = mBookAdapter

        sell_recycle.layoutManager = LinearLayoutManager(this@TradeCommonActivity, RecyclerView.VERTICAL, false)
        mBookAdapter = OrderBookAdapter(transactionList, this@TradeCommonActivity, 2)
        sell_recycle.adapter = mBookAdapter

        tradeRecycle.layoutManager = LinearLayoutManager(this@TradeCommonActivity, RecyclerView.VERTICAL, false)
        mLasttransactionAdapter = LatestTransactionAdapter(transactionList, this@TradeCommonActivity, 1)
        tradeRecycle.adapter = mLasttransactionAdapter

        selectedbutton(mBuytitlebtn, mSelltitlebtn)

        mBuytitlebtn.setOnClickListener {
            selectedbutton(mBuytitlebtn, mSelltitlebtn)
            mTradeType="buy"
            mSubmitBtn.setText("BUY")
            mSubmitBtn.setBackgroundResource(R.drawable.rounded_corner_green)
        }

        mSelltitlebtn.setOnClickListener {
            selectedbutton(mSelltitlebtn, mBuytitlebtn)
            mTradeType="sell"
            mSubmitBtn.setText("SELL")
            mSubmitBtn.setBackgroundResource(R.drawable.rounded_corner_red)
        }

        mTradespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                makePairDetails()
            }
        }

        mAmounttxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                if (!mAmounttxt.text.isNullOrEmpty()) {
                    mMakeCalculation(mAmounttxt.text.toString())

                } else {
                    mMakerfee.setText("0.00000000")
                    mTakerfee.setText("0.00000000")
                }
            }

        })

        mPricetxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                if (!mPricetxt.text.isNullOrEmpty()) {
                    mtotMakeCalculation(mPricetxt.text.toString())

                } else {
                    /*mMakerfee.setText("0.00000000")
                    mTakerfee.setText("0.00000000")*/
                }
            }

        })


        mSubmitBtn.setOnClickListener {
            if(mPricetxt.text.toString().length==0){
                Toast.makeText(this@TradeCommonActivity, "Please fill price", Toast.LENGTH_SHORT).show()
            }else if(mAmounttxt.text.toString().length==0){
                Toast.makeText(this@TradeCommonActivity, "Please fill amount", Toast.LENGTH_SHORT).show()
            }else{
                mEmitCreateorder()
            }
        }


    }



    fun initvariable(){

        var app = MyApplication()
        mSocket=app.socket
        mSocket!!.on(Socket.EVENT_CONNECT, onConnect)
        mSocket!!.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket!!.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket!!.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)
        mSocket!!.on("emitPairsResponse", onNewMessage)
        mSocket!!.on("userResponse", onUserMessage)
        mSocket!!.on("createResponse", onCreatereposne)
        mSocket!!.on(Socket.EVENT_ERROR, onError)
        mSocket!!.connect()
    }


    fun selectedbutton(viewsel: Button, viewunsel1: Button) {
        if (this != null) {
            viewsel.setBackgroundResource(R.drawable.bottom_line)
            viewunsel1.setBackgroundResource(0)
        }
    }

    private fun mMakeCalculation(s: String) {
        try {
            var makerFees:Double?=0.0
            var takerFees:Double?=0.0
            var total:Double?=0.0

            //Maker calc
            makerFees = s.toDouble() * mMaker!!.toDouble() / 100
            mMakerfee.setText(String.format("%.8f", makerFees))

            //Taker calc
            takerFees = s.toDouble() * mTaker!!.toDouble() / 100
            mTakerfee.setText(String.format("%.8f", takerFees))

            //Total calc
            total=s.toDouble()* mPricetxt?.text.toString().toDouble()
            mTotal.setText(String.format("%.8f", total))


        } catch (e: NumberFormatException) {
        }

    }

    private fun mtotMakeCalculation(s: String) {
        try {

            var total:Double?=0.0

            //Total calc
            total=s.toDouble()* mAmounttxt?.text.toString().toDouble()
            mTotal.setText(String.format("%8f", total))

        } catch (e: NumberFormatException) {
        }

    }


    fun mOrderpopup() {
        var v_dialog = Dialog(this)
        v_dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        v_dialog?.setCancelable(false)
        v_dialog?.setContentView(R.layout.popup_layout)

        v_dialog!!.getWindow()?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        v_dialog?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        v_dialog?.window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        v_dialog?.window!!.setGravity(Gravity.BOTTOM);
        v_dialog?.show()


        v_dialog?.mMarkettxt?.setOnClickListener {
            mOrderType="market"
            mOrderTypetxt.setText("Market")
            v_dialog?.dismiss()
        }

        v_dialog?.mLimittxt?.setOnClickListener {
            mOrderType="limit"
            mOrderTypetxt.setText("Limit")
            v_dialog?.dismiss()
        }

        v_dialog?.mCanceltxt?.setOnClickListener {
            v_dialog?.dismiss()
        }
    }

    private fun mGetpair() {
        pairList!!.clear()
        tradepairList!!.clear()
        val params = HashMap<String, String>()


        servicerequestCall?.makeStringRequestGet(
            URLConstants.homemarket,
            this@TradeCommonActivity, params, object : DataCallback1 {
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
                            spinnertypeArray = ArrayAdapter(this@TradeCommonActivity, R.layout.spinner_item, pairList!!)
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
                    mMaker=tradepairList.get(i).maker
                    mTaker=tradepairList.get(i).taker

                    //text value
                    mMakertxt.setText("Maker("+tradepairList.get(i).maker+"%)")
                    mTakertxt.setText("Taker("+tradepairList.get(i).taker+"%)")

                } else {
                }
            }
        }
    }



//Socket connection

    private val onConnect = Emitter.Listener {
        runOnUiThread(Runnable {

            if (isConnected) {
                Toast.makeText(this,"Connected", Toast.LENGTH_LONG).show()
            }
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
                    if(data.optString("status").equals("1")){
                        Toast.makeText(this@TradeCommonActivity, "Order placed successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@TradeCommonActivity, "Error occured", Toast.LENGTH_SHORT).show()
                    }

                }
            })
        }
    }


    private val onError = Emitter.Listener { args ->
        Log.e("data", "" + args[0])
    }

    fun mEmitCreateorder(){
        val obj = JSONObject()
        try {
            obj.put("userid", urlConstants!!.tokenaddrs)
            obj.put("amount", mAmounttxt.text.toString())
            obj.put("price", mPricetxt.text.toString())
            obj.put("pair", mPairID)
            obj.put("ordertype", mOrderType)
            obj.put("type", mTradeType)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val message = obj.toString()
        Log.e("Param=", message)
        mSocket?.emit("createOrder", message);

       /* val params = HashMap<String, Any>()
        try {
            params.put("userid", urlConstants!!.tokenaddrs)
            params.put("amount",2)
            params.put("price", 1)
            params.put("pair", mPairID!!)
            params.put("ordertype", mOrderType!!)
            params.put("type", mTradeType!!)

        } catch (e: JSONException) {
            Log.e("error" , e.toString())
        }

        val message = params.toString()
        Log.e("Param=", message)
        mSocket?.emit("createOrder", message);*/
    }

}
