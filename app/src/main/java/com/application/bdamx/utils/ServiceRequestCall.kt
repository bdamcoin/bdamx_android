package com.application.bdamx.Utils



import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.application.bdamx.R
import com.application.bdamx.utils.DataCallback1
import com.application.bdamx.utils.ServiceHandler
import kotlinx.android.synthetic.main.popup_common.*
import org.json.JSONObject
import java.util.*


/**
 * Created by devel-91 on 2/4/18.
 */


class ServiceRequestCall(context: Context) {
    internal var urlConstants: URLConstants
    internal var obj: ServiceHandler? = null
    // var v_dialog :Dialog?=null
    var isshown: Boolean=false
    init {

        getRequestQueueInstance(context)
        urlConstants = URLConstants(context)

    }

    fun customObjectRequest(url: String, context: Context, jsonObject: JSONObject, callback: DataCallback) {

        val dialog = getProgressDialog(context)
        dialog.show()
        //  v_dialog = Dialog(context)
        Log.e("url", "" + url)
        Log.e("TAG", jsonObject.toString() + "")


        val request = object : JsonObjectRequest(Method.POST,
                url, jsonObject, Response.Listener { response ->
            dialog.dismiss()
            //   Log.e("TAG", response.toString() + "");
            try {
                // JSONObject object = new JSONObject(response.toString());
                Log.e("ResponseJSON", response.toString())


                /*  var chunkSize:Int?= 2048

                  for(i in  0 until response.length())
                  {
                      Log.e("RESPONSE",""+response.toString().substring(i, Math.min(response.length(), i + chunkSize!!)));
                  }*/


/*  final int chunkSize = 2048;
                    for (int i = 0; i < response.length(); i += chunkSize) {
                        Log.e("RESPONSE", response.substring(i, Math.min(response.length(), i + chunkSize)));
                    }*/

                callback.onSuccess(response)

            } catch (e: Exception) {
                Log.e("Erooror", "eroor", e)
            }
        },
                Response.ErrorListener { error ->
                    dialog.dismiss()
                    Log.e("Error--", error.toString())
                    if (error is NoConnectionError) {
                        //  Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                       // if(!isshown)
                          //  showdialog1(url, context, jsonObject, callback, context.getString(R.string.internet_connection))


                    }
                    if (error is ServerError) {
                        Toast.makeText(context, context.getString(R.string.no_internet) + error, Toast.LENGTH_SHORT).show()
                       // if(!isshown)
                           // showdialog1(url, context, jsonObject, callback, context.getString(R.string.went_wrong))
                    }
                }) {

            override fun getParams(): Map<String, String> {
                return HashMap()
            }

/*            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                val creds = String.format("%s:%s", "BdccAPPkey", "GE4515FGG$$345R$\$f")
                val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.DEFAULT)
                params.put("Authorization", auth)
                return params
            }*/
        }
        request.retryPolicy = DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        mRequestQueue!!.add(request)
    }

    fun customObjectRequest2(url: String, context: Context, jsonObject: JSONObject, callback: DataCallback) {

        val dialog = getProgressDialog(context)
        dialog.show()
        //  v_dialog = Dialog(context)
        Log.e("url", "" + url)
        Log.e("TAG", jsonObject.toString() + "")


        val request = object : JsonObjectRequest(Method.GET,
                url, jsonObject, Response.Listener { response ->
            dialog.dismiss()
            //   Log.e("TAG", response.toString() + "");
            try {
                // JSONObject object = new JSONObject(response.toString());
                Log.e("ResponseJSON", response.toString())
                callback.onSuccess(response)

            } catch (e: Exception) {
                Log.e("Erooror", "eroor", e)
            }
        },
                Response.ErrorListener { error ->
                    dialog.dismiss()
                    Log.e("Error--", error.toString())
                    if (error is NoConnectionError) {
                        //  Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        //if(!isshown)
                            //showdialog1(url, context, jsonObject, callback, context.getString(R.string.internet_connection))


                    }
                    if (error is ServerError) {
                        //Toast.makeText(context, context.getString(R.string.no_internet) + error, Toast.LENGTH_SHORT).show()
                        //if(!isshown)
                            //showdialog1(url, context, jsonObject, callback, context.getString(R.string.went_wrong))
                    }
                }) {

            override fun getParams(): Map<String, String> {
                return HashMap()
            }

/*            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                val creds = String.format("%s:%s", "BdccAPPkey", "GE4515FGG$$345R$\$f")
                val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.DEFAULT)
                params.put("Authorization", auth)
                return params
            }*/
        }
        request.retryPolicy = DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        mRequestQueue!!.add(request)
    }

    fun customObjectRequest1(url: String, context: Context, jsonObject: JSONObject, callback: DataCallback) {

        //val dialog = getProgressDialog(context)
        //dialog.show()
        // v_dialog = Dialog(context)

        Log.e("url", "" + url)
        Log.e("TAG", jsonObject.toString() + "")


        val request = object : JsonObjectRequest(Method.POST,
                url, jsonObject, Response.Listener { response ->
             //dialog.dismiss()
            //   Log.e("TAG", response.toString() + "");
            try {
                // JSONObject object = new JSONObject(response.toString());
                Log.e("ResponseJSON", response.toString())
                callback.onSuccess(response)

            } catch (e: Exception) {
                Log.e("Erooror", "eroor", e)
            }
        },
                Response.ErrorListener { error ->
                         //dialog.dismiss()
                    Log.e("Error--", error.toString())
                   /* if (error is NoConnectionError) {
                        //  Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        if(!isshown)
                            showdialog1(url, context, jsonObject, callback, context.getString(R.string.internet_connection))
                    }
                    if (error is ServerError) {
                        Toast.makeText(context, context.getString(R.string.no_internet) + error, Toast.LENGTH_SHORT).show()
                        if(!isshown)
                            showdialog1(url, context, jsonObject, callback, context.getString(R.string.went_wrong))
                    }*/
                }) {

            override fun getParams(): Map<String, String> {
                return HashMap()
            }

/*            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                val creds = String.format("%s:%s", "BdccAPPkey", "GE4515FGG$$345R$\$f")
                val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.DEFAULT)
                params.put("Authorization", auth)
                return params
            }*/
        }
        request.retryPolicy = DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        mRequestQueue!!.add(request)
    }

    fun customObjectRequestGet(url: String, context: Context, jsonObject: JSONObject, callback: DataCallback) {

        val dialog = getProgressDialog(context)
        dialog.show()
        // v_dialog = Dialog(context)

        Log.e("url", "" + url)
        Log.e("TAG", jsonObject.toString() + "")


        val request = object : JsonObjectRequest(Method.GET,
            url, jsonObject, Response.Listener { response ->
                 dialog.dismiss()
                //   Log.e("TAG", response.toString() + "");
                try {
                    // JSONObject object = new JSONObject(response.toString());
                    Log.e("ResponseJSON", response.toString())
                    callback.onSuccess(response)

                } catch (e: Exception) {
                    Log.e("Erooror", "eroor", e)
                }
            },
            Response.ErrorListener { error ->
                     dialog.dismiss()
                Log.e("Error--", error.toString())
                if (error is NoConnectionError) {
                    //  Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    //if(!isshown)
                      //  showdialog1(url, context, jsonObject, callback, context.getString(R.string.internet_connection))
                }
                if (error is ServerError) {
                    Toast.makeText(context, context.getString(R.string.no_internet) + error, Toast.LENGTH_SHORT).show()
                   // if(!isshown)
                       // showdialog1(url, context, jsonObject, callback, context.getString(R.string.went_wrong))
                }
            }) {

            override fun getParams(): Map<String, String> {
                return HashMap()
            }

/*            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                val creds = String.format("%s:%s", "BdccAPPkey", "GE4515FGG$$345R$\$f")
                val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.DEFAULT)
                params.put("Authorization", auth)
                return params
            }*/
        }
        request.retryPolicy = DefaultRetryPolicy(
            0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        mRequestQueue!!.add(request)
    }


    fun makeStringRequest1(url: String, context: Context, params: Map<String, String>, callback: DataCallback1) {
        val dialog = getProgressDialog(context)
        dialog.show()

        Log.e("URL", url)
        Log.e("PARAMS",""+ params)
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
            /* //val jsonResult = JSONTokener(response).nextValue()
            // Log.e("response", response)*/
            dialog.dismiss()
            callback.onSuccess(response)
        }, Response.ErrorListener { error ->
            dialog.dismiss()
            Log.e("error", error.toString())
// callback.onError(error)

            if (error is NoConnectionError) {
 Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                if(!isshown)
                    showdialog1(url, context, params, callback, "Please check your internet connection and try again")
            }
            if (error is ServerError) {
 Toast.makeText(context, "No Internet Connection" + error, Toast.LENGTH_SHORT).show()
                if(!isshown)
                    showdialog1(url, context, params, callback, "Something went wrong in server")
            }
        }) {
            override fun getParams(): Map<String, String> {
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Accept-Charset", "utf-8")
//headers.put("Accept", RITEAID_HTTP_CONTENT_TYPE);
                return headers;
            }
        }
        request.retryPolicy = DefaultRetryPolicy(0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        mRequestQueue!!.add(request)
    }

    fun makeStringRequestwithoutloader(url: String, context: Context, params: Map<String, String>, callback: DataCallback1) {
        //val dialog = getProgressDialog(context)
        //dialog.show()

        Log.e("URL", url)
        Log.e("PARAMS",""+ params)
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
            /* //val jsonResult = JSONTokener(response).nextValue()
            // Log.e("response", response)*/
            //dialog.dismiss()
            callback.onSuccess(response)
        }, Response.ErrorListener { error ->
            //dialog.dismiss()
            Log.e("error", error.toString())
// callback.onError(error)

            if (error is NoConnectionError) {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                if(!isshown)
                showdialog1(url, context, params, callback, "Please check your internet connection and try again")
            }
            if (error is ServerError) {
                Toast.makeText(context, "No Internet Connection" + error, Toast.LENGTH_SHORT).show()
                if(!isshown)
                 showdialog1(url, context, params, callback, "Something went wrong in server")
            }
        }) {
            override fun getParams(): Map<String, String> {
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Accept-Charset", "utf-8")
//headers.put("Accept", RITEAID_HTTP_CONTENT_TYPE);
                return headers;
            }
        }
        request.retryPolicy = DefaultRetryPolicy(0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        mRequestQueue!!.add(request)
    }

    fun makeStringRequestGet(url: String, context: Context, params: Map<String, String>, callback: DataCallback1) {
        val dialog = getProgressDialog(context)
        dialog.show()

        Log.e("URL", url)
        Log.e("PARAMS",""+ params)
        val request = object : StringRequest(Request.Method.GET, url, Response.Listener { response ->
            /* //val jsonResult = JSONTokener(response).nextValue()
            // Log.e("response", response)*/
            dialog.dismiss()
            callback.onSuccess(response)
        }, Response.ErrorListener { error ->
            dialog.dismiss()
            Log.e("error", error.toString())
// callback.onError(error)

            if (error is NoConnectionError) {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                if(!isshown)
                    showdialog1(url, context, params, callback, "Please check your internet connection and try again")
            }
            if (error is ServerError) {
                Toast.makeText(context, "No Internet Connection" + error, Toast.LENGTH_SHORT).show()
                if(!isshown)
                    showdialog1(url, context, params, callback, "Something went wrong in server")
            }
        }) {
            override fun getParams(): Map<String, String> {
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Accept-Charset", "utf-8")
//headers.put("Accept", RITEAID_HTTP_CONTENT_TYPE);
                return headers;
            }
        }
        request.retryPolicy = DefaultRetryPolicy(0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        mRequestQueue!!.add(request)
    }

    fun makeStringRequest1witheader(url: String, context: Context, params: Map<String, String>, callback: DataCallback1) {
        val dialog = getProgressDialog(context)
        ////dialog.show()

        Log.e("URL", url)
        Log.e("PARAMS",""+ params)
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
            /* //val jsonResult = JSONTokener(response).nextValue()
            // Log.e("response", response)*/
            ////dialog.dismiss()
            callback.onSuccess(response)
        }, Response.ErrorListener { error ->
            ////dialog.dismiss()
            Log.e("error", error.toString())
// callback.onError(error)

            if (error is NoConnectionError) {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                if(!isshown)
                    showdialog1(url, context, params, callback, "Please check your internet connection and try again")
            }
            if (error is ServerError) {
                Toast.makeText(context, "No Internet Connection" + error, Toast.LENGTH_SHORT).show()
                if(!isshown)
                    showdialog1(url, context, params, callback, "Something went wrong in server")
            }
        }) {
            override fun getParams(): Map<String, String> {
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Accept-Charset", "utf-8")
                headers.put("Content-Type", "multipart/form-data");
                headers.put("Authorization", "Token token=sdk_live.Kg5EDhc9Vcf.bPgfdZN5Dkgc7z-Yj8fIi5eQlEEA92EG");
//headers.put("Accept", RITEAID_HTTP_CONTENT_TYPE);
                println("header=="+headers.toString())
                return headers;
            }
        }
        request.retryPolicy = DefaultRetryPolicy(0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        mRequestQueue!!.add(request)
    }



    fun showdialog1(url: String, context: Context, s1: Map<String, String>, callback: DataCallback1, msg1: String) {

        try {
            Log.e("ERRR==","else")
            var v_dialog= Dialog(context)
            v_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            v_dialog.setCancelable(false)
            v_dialog.setContentView(R.layout.popup_common)
            v_dialog!!.getWindow()?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
            //val img = v_dialog.findViewById<View>(R.id.img) as ImageView

            //img.visibility = View.VISIBLE

            val ok = v_dialog.findViewById<View>(R.id.ok) as TextView
            val txtmsg = v_dialog.findViewById<View>(R.id.txtmsg) as TextView
            v_dialog.mtitle_pop.setText("No Internet connection")
            v_dialog.ok.setText("Retry")
            txtmsg.text = msg1
            ok.setOnClickListener {
                isshown=false
                makeStringRequest1(url, context, s1, callback)
                v_dialog.dismiss()

            }
            v_dialog.cancel.setOnClickListener {
                v_dialog.dismiss()

            }
            isshown=true

            //window.setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT)

            v_dialog?.window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
            v_dialog?.show()

        }catch (e: java.lang.Exception) {

        }

    }



/*
    fun showdialogString(url: String, context: Context, j1: StringRequest, callback: DataCallback, msg1: String) {

        try {
            Log.e("ERRR==","else")
            var v_dialog= Dialog(context)
            v_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            v_dialog.setCancelable(true)
            v_dialog.setContentView(R.layout.custom_dialog)
            v_dialog!!.getWindow().setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
            val img = v_dialog.findViewById<View>(R.id.img) as ImageView

            img.visibility = View.VISIBLE

            val ok = v_dialog.findViewById<View>(R.id.ok) as Button
            val txtmsg = v_dialog.findViewById<View>(R.id.txtmsg) as TextView
            txtmsg.text = msg1
            ok.setOnClickListener {
                isshown=false
                makeStringRequest1(url, context, j1, callback)
                v_dialog.dismiss()

            }
            isshown=true

            //window.setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT)

            v_dialog?.window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
            v_dialog?.show()

        }catch (e: java.lang.Exception) {

        }

    }
*/


    fun getProgressDialog(mContext: Context): Dialog {
        val progressDialog = Dialog(mContext)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.custom_progress)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        progressDialog!!.show()

        return progressDialog
    }

    companion object {
        private var mRequestQueue: RequestQueue? = null
        @Synchronized
        fun getRequestQueueInstance(context: Context): RequestQueue? {
            if (mRequestQueue == null) {
                try {
                    val handler = ServiceHandler(context)
                    // URL url = new URL(URLConstants.BASEURL);
                    if (URLConstants.https_status == "0") {
                        mRequestQueue = Volley.newRequestQueue(context)
                    } else {
                        val hurlStack = handler.makeHttpRequest()
                        mRequestQueue = Volley.newRequestQueue(context, hurlStack)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return mRequestQueue
        }
    }


}