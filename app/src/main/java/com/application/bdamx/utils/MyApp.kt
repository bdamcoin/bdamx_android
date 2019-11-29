package com.application.bdamx

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import org.json.JSONException
import org.json.JSONObject


class MyApp : Application() {
    var servicerequestCall1: ServiceRequestCall? = null
    var urlConstants1: URLConstants? = null
    var handler: Handler? = null
    var myRunnable: Runnable? = null

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        servicerequestCall1 = ServiceRequestCall(this)
        urlConstants1 = URLConstants(this)

        /*val appSignature = AppSignatureHelper(this)
        Log.v("AppSignature", appSignature.appSignatures.toString())

        // Set up Crashlytics, disabled for debug builds
        val crashlyticsKit = Builder()
                .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build()
        Fabric.with(this, crashlyticsKit)*/





            //initvariable()
    }

    companion object {


        // Singleton instance
        private var sInstance: MyApp? = null


        // Getter to access Singleton instance
        val instance: MyApp
            get() {

                if (sInstance == null)
                    sInstance = MyApp()
                return sInstance!!
            }
    }

    fun initvariable(){

        try {

            handler = getHandlerInst()
            myRunnable = Runnable {
                try {
                    //prepare and send the data here..

                    if(urlConstants1?.userid!=null&&!urlConstants1?.userid.equals("")&&!urlConstants1?.userid.equals("")){
                       // mGetUserstatus()
                    }else{

                    }

                    handler!!.postDelayed(myRunnable, 2000)

                    //handler.removeCallbacks(myRunnable);

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            handler!!.post(myRunnable)
        } catch (e: Exception) {

        }

        /*if(urlConstants1?.userid!=null&&!urlConstants1?.userid.equals("")&&!urlConstants1?.userid.equals("")){
            handler!!.post(myRunnable)
        }else{
            handler?.removeCallbacks(myRunnable);
        }*/
        //handler!!.post(myRunnable)
    }



    fun getHandlerInst(): Handler {
        if (handler == null) {
            synchronized(Handler::class.java) {
                if (handler == null) {
                    handler = Handler()
                }
            }
        }
        return handler!!
    }

}
