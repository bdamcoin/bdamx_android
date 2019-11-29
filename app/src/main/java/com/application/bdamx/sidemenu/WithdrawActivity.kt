package com.application.bdamx.sidemenu

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.login.HomeActivity
import com.application.bdamx.utils.DataCallback1
import com.google.android.gms.vision.barcode.Barcode
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_withdraw.*
import org.json.JSONException
import org.json.JSONObject

class WithdrawActivity : BaseActivity() {
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    var min: String? = null
    var max: String? = null
    var fee: String? = null
    var mTotalAmtFiat: Double? = 0.0
    private val REQUESTCODE = 101
    private val TAG = WithdrawActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)

        servicerequestCall = ServiceRequestCall(this@WithdrawActivity)
        urlConstants = URLConstants(this@WithdrawActivity)

       initivariable()

        qrcodescan.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED) {
                    //Requesting permission.
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CAMERA),
                        1
                    )
                } else {
                    val i = Intent(this@WithdrawActivity, QrCodeScannerActivity::class.java)
                    startActivityForResult(i, REQUESTCODE)
                }
            } else {
                val i = Intent(this@WithdrawActivity, QrCodeScannerActivity::class.java)
                startActivityForResult(i, REQUESTCODE)
            }

        }



        withdrawAmounttxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (!withdrawAmounttxt.text.isNullOrEmpty()) {
                    mMakeCalculation(withdrawAmounttxt.text.toString())
                } else {
                    feesamountxt.setText("0.00")
                    TotalAmount.setText("0.00")
                }
            }
        })

        Withdrawbtn.setOnClickListener {
            if(sendAddresstxt.text.toString().length==0){
                Toast.makeText(this@WithdrawActivity, "Please fill withdraw address", Toast.LENGTH_SHORT).show()
            }else if(withdrawAmounttxt.text.toString().length==0){
                Toast.makeText(this@WithdrawActivity, "Please fill amount", Toast.LENGTH_SHORT).show()
            }
            else if (!withdrawAmounttxt.text.toString().isEmpty() && withdrawAmounttxt.text.toString() != null && withdrawAmounttxt.text.toString().toDouble() > max!!.toDouble()) {
                Toast.makeText(this@WithdrawActivity, "Please Enter A Value Less Than Or Equal To $max", Toast.LENGTH_SHORT).show()

            } else if (!withdrawAmounttxt.text.toString().isEmpty() && withdrawAmounttxt.text.toString() != null && withdrawAmounttxt.text.toString().toDouble() < min!!.toDouble()) {
                Toast.makeText(this@WithdrawActivity, "Please Enter A Value Greater Than Or Equal To $min", Toast.LENGTH_SHORT).show()
            }else{
                makeWithdraw()
            }
        }

    }

    fun initivariable(){
        var I = intent
        var blnc = I.getStringExtra("blnc")
        var order = I.getStringExtra("order")
        min = I.getStringExtra("min")
        max = I.getStringExtra("max")
        fee = I.getStringExtra("fee")

        if (blnc != null && !blnc.equals("") && !blnc.equals("null")) {
            totalamount.setText(Cryptoformat.format(blnc?.toDouble()) + " " + urlConstants?.nativecurrency)
        }

        if (min != null && !min.equals("") && !min.equals("null")) {
            minwithdraw.setText(Cryptoformat.format(min?.toDouble()) + " " + urlConstants?.nativecurrency)
        }

        if (max != null && !max.equals("") && !max.equals("null")) {
            maxWithdraw.setText(Cryptoformat.format(max?.toDouble()) + " " + urlConstants?.nativecurrency)
        }

        if (fee != null && !fee.equals("") && !fee.equals("null")) {
            feestxt.setText("Fee ("+fee+" %)")
        }

        if (urlConstants?.tokenaddrs != null && !urlConstants?.tokenaddrs.equals("") && !urlConstants?.tokenaddrs.equals("null")) {
            coinAddress.setText(urlConstants?.tokenaddrs)
        }
    }

    private fun mMakeCalculation(s: String) {
        try {
            var fees:Double?=0.0

            fees = s.toDouble() * fee!!.toDouble() / 100
            println("fee==" + fees + "==" + s.toDouble() + "==" + fee!!.toDouble())
            feesamountxt.setText(String.format("%.8f", fees)+" "+urlConstants?.nativecurrency)
            println("dsdfsdf==" + s.toDouble() + "==" + fees)
            mTotalAmtFiat = s?.toDouble() - fees!!

            var mTotalAmt = mTotalAmtFiat
            println("mGetUSDvalue==" + mTotalAmt)

            TotalAmount.setText(String.format("%.8f", mTotalAmt)+" "+urlConstants?.nativecurrency)
        } catch (e: NumberFormatException) {

        }

    }


    private fun makeWithdraw() {
        val params = HashMap<String, String>()
        try {
            //params.put("toAddress","2NBPnApkFFFy2t6XZo7zWGjSx6iArkMbnNe")
            params.put("toAddress",sendAddresstxt.text.toString())
            params.put("useraddress", coinAddress.text.toString())
            params.put("withamount", withdrawAmounttxt.text.toString())
            params.put("type", "user")

        } catch (e: JSONException) {
            Log.e(TAG, "error" + e.toString())
        }

        servicerequestCall?.makeStringRequest1(URLConstants.withdraw,
            this@WithdrawActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("withdraw-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if (status.equals("true")) {
                            Toast.makeText(this@WithdrawActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@WithdrawActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val barcode = data.getParcelableExtra<Barcode>("barcode")
                sendAddresstxt.post(Runnable // Use the post method of the TextView
                {
                    var s = barcode.displayValue
                    var s1 = s.replace("bitcoin:", "")
                    var l = s1.length
                    if (s1.indexOf("?") != -1) {
                        var s2 = s1.indexOf("?")
                        s = s1.removeRange(s2, l)
                    } else {
                        s = s1
                    }
                    sendAddresstxt.setText(s.replace("\'", ""))
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updatetoolbar(getString(R.string.withdraw),false)

    }
}
