package com.application.bdamx.login

import android.app.ActionBar
import android.app.AlertDialog
import android.app.Dialog
import android.content.*
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer

import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.bdamx.R
import kotlinx.android.synthetic.main.toast_layout.view.*
import kotlinx.android.synthetic.main.toolbar_header.*
import kotlinx.android.synthetic.main.toolbar_header.txttitle
import kotlinx.android.synthetic.main.toolbar_header_common.*
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern


open class BaseActivity : AppCompatActivity() {

    protected var mView: View? = null
    protected lateinit var mContext: Context
    lateinit var dialog1: AlertDialog.Builder
    val Cryptoformat = DecimalFormat("#0.00000000")
    //private var urlConstants: URLConstants? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        dialog1 = AlertDialog.Builder(mContext)
       // urlConstants = URLConstants(mContext)

        //manager = SessionManager.getInstance(this)
    }

    /*fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0)
    }*/

    protected fun getClasName(): String {
        return this::class.java.simpleName
    }

    fun formatDecimal(text: String): String {
        val format = DecimalFormat("#0.00")
        return format.format(java.lang.Double.parseDouble(text.trim { it <= ' ' }))
    }


    fun formatCryptoCurrency(text: String): String {
        val format = DecimalFormat("#0.00000000")
        return format.format(java.lang.Double.parseDouble(text.trim { it <= ' ' }))
    }

    fun updatetoolbar(title: String,visible: Boolean) {
        //setSupportActionBar(toolbar)
        //supportActionBar?.title = ""
        txttitle.setText(title)

        back_img.setOnClickListener {
            finish()
        }

        if(visible){
            //video_img.visibility=View.VISIBLE
        }else{
            //video_img.visibility=View.INVISIBLE
        }

    }

    fun emailValidator(mailAddress: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(mailAddress)
        return matcher.matches()
    }

    fun showToastMessage(context: Context, message: String) {

        val inflater = LayoutInflater.from(context)

        val toastDialog = inflater.inflate(R.layout.toast_layout, null)
        val toastDurationInMilliSeconds = 5000
        val toastCountDown: CountDownTimer
        toastDialog!!.toast_text.text=message
        val toast = Toast(context)
        toast.setGravity(Gravity.BOTTOM or Gravity.FILL_HORIZONTAL, 0, 0)
        toast.view = toastDialog

        toastCountDown = object : CountDownTimer(toastDurationInMilliSeconds.toLong(), 1000 /*Tick duration*/) {
            override fun onTick(millisUntilFinished: Long) {
                toast.show()
            }

            override fun onFinish() {
                toast.cancel()
            }
        }

        // Show the toast and starts the countdown
        toast.show()
        toastCountDown.start()
    }



}