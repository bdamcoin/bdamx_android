package com.dcwallet.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.application.bdamx.R
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Created by devel-70 on 20/10/18.
 */
open class BaseFragment : Fragment() {

    protected var mView: View? = null
    // var web: Webservice?=null
    /*  var readWebService: ReadWebService?=null*/
    var mcontext: FragmentActivity?=null
    //  lateinit var loginService : Dataservice
    var dialog: ProgressDialog?=null
    //    var v1: ValidateUtil?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mcontext=activity
//        v1= ValidateUtil()
        // web= Webservice.getwebservice(mcontext!!)
        // dialog=ProgressDialog(mcontext)
        //  dialog!!.setMessage("Loading")

        //  loginService =  ServiceGenerator.createService(Dataservice::class.java, BuildConfig.Username_ApiKey, BuildConfig.Password_ApiKey)

    }

    fun showtoast(msg:String)
    {
        Toast.makeText(mcontext,msg,Toast.LENGTH_SHORT).show()

    }

    fun nointernetconnect(type:Int)
    {
        if(type==1)
            Toast.makeText(mcontext, mcontext!!.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(mcontext, mcontext!!.getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()

    }
    fun formatdecimal(text:String):String
    {
        val format= DecimalFormat("#0.00")
        var bd= BigDecimal(text.toDouble())
        bd=bd.setScale(3, BigDecimal.ROUND_HALF_UP)
        return format.format(bd.toDouble())
    }
    open fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {}
    open fun onNothingSelected(arg0: AdapterView<*>) {}
    fun hidekeyborad()
    {
        // Check if no view has focus:
        val view = mcontext!!.currentFocus
        if (view != null) {
            val imm = mcontext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
/*
    fun loadFragmentwitbackstack(fragment: Fragment?,context: FragmentActivity?): Boolean { //function replace bottom navigation
        if (fragment != null) {
            // if (mPref!!.isLoggedIn()) {
            context!!.supportFragmentManager .beginTransaction()
                .setCustomAnimations(R.anim.anim_slide_in_left, 0)
                .replace(R.id.mFrameContainer, fragment)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }
*/
}