package com.application.bdamx.sidemenu

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.login.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_deposit.*

class DepositActivity : BaseActivity() {
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)

        servicerequestCall = ServiceRequestCall(this@DepositActivity)
        urlConstants = URLConstants(this@DepositActivity)

        var I = intent
        var blnc = I.getStringExtra("blnc")
        var order = I.getStringExtra("order")
        var min = I.getStringExtra("min")
        var max = I.getStringExtra("max")
        var fee = I.getStringExtra("fee")

        if (blnc != null && !blnc.equals("") && !blnc.equals("null")) {
            totalamount.setText(Cryptoformat.format(blnc?.toDouble()) + " " + urlConstants?.nativecurrency)
        }

        if (order != null && !order.equals("") && !order.equals("null")) {
            availableamount.setText(Cryptoformat.format(order?.toDouble()) + " " + urlConstants?.nativecurrency)
        }

        if (urlConstants?.tokenaddrs != null && !urlConstants?.tokenaddrs.equals("") && !urlConstants?.tokenaddrs.equals("null")) {
            coinAddress.setText(urlConstants?.tokenaddrs)
            Picasso.with(this@DepositActivity)
                .load("https://chart.googleapis.com/chart?cht=qr&chs=150x150&chl='" + urlConstants?.tokenaddrs + "'&choe=UTF-8&chld=L'")
                .into(scanaddressimg)

            println("img===" + "https://chart.googleapis.com/chart?cht=qr&chs=150x150&chl='" + urlConstants?.tokenaddrs + "'&choe=UTF-8&chld=L'")
        }

        copyimg.setOnClickListener {

            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.text = coinAddress.getText()
            Toast.makeText(this@DepositActivity, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        shareimg.setOnClickListener {
            val shareBody = coinAddress.text.toString()
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

    }

    override fun onResume() {
        super.onResume()
        updatetoolbar("Deposit", false)

    }
}
