package com.application.bdamx.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.utils.DataCallback1
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : BaseActivity() {
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    var ipAddress: String? = null
    private val TAG = RegisterActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        servicerequestCall = ServiceRequestCall(this)
        urlConstants = URLConstants(this)

        mLogin_txt.setOnClickListener {
            var I= Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(I)
        }

        mSendotpbtn.setOnClickListener {
            if(mEmailEdittext.text.toString().length==0){
                Toast.makeText(this@RegisterActivity, "Please fill email ID", Toast.LENGTH_SHORT).show()
            }else if (!emailValidator(mEmailEdittext.text.toString())) {
                Toast.makeText(this@RegisterActivity, "Please fill valid email ID", Toast.LENGTH_LONG).show()
            }else{
              mGetotp()
            }
        }

        mSubmitbtn.setOnClickListener {
            if(mEmailEdittext.text.toString().length==0){
                Toast.makeText(this@RegisterActivity, "Please fill email ID", Toast.LENGTH_SHORT).show()
            }else if (!emailValidator(mEmailEdittext.text.toString())) {
                Toast.makeText(this@RegisterActivity, "Please fill valid email ID", Toast.LENGTH_LONG).show()
            }else if (mOtpEdittext.text.toString().length==0) {
                Toast.makeText(this@RegisterActivity, "Please fill OTP", Toast.LENGTH_SHORT).show()
            }
            else if (!mTermscheck.isChecked) {
                Toast.makeText(this@RegisterActivity, "Agree terms and condition ", Toast.LENGTH_SHORT).show()
            }
            else{
                makeRegister()
            }
        }

        mGetIP()
    }


    private fun makeRegister() {
        val params = HashMap<String, String>()
        try {
            params.put("email", mEmailEdittext.text.toString())
            params.put("otp", mOtpEdittext.text.toString())
            if(ipAddress!=null&&!ipAddress.equals("")&&!ipAddress.equals("null")){
                params.put("ip", ipAddress!!)
            }

        } catch (e: JSONException) {
            Log.e(TAG, "error" + e.toString())
        }

        servicerequestCall?.makeStringRequest1(URLConstants.register,
            this@RegisterActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("Register-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if (status.equals("true")) {
                            Toast.makeText(this@RegisterActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()

                            urlConstants!!.tokenaddrs=Json.optString("originaladdress")
                            var I= Intent(this@RegisterActivity,HomeActivity::class.java)
                            startActivity(I)
                        } else {
                            Toast.makeText(this@RegisterActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }


    private fun mGetotp() {
        val params = HashMap<String, String>()
        try {
            params.put("email", mEmailEdittext.text.toString())

        } catch (e: JSONException) {
            Log.e(TAG, "error" + e.toString())
        }

        servicerequestCall?.makeStringRequest1(URLConstants.RegOTPSend,
            this@RegisterActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("GETOTP-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        println("status==="+status)
                        if (status.equals("true")) {
                            //Toast.makeText(this@RegisterActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()

                            showToastMessage(this@RegisterActivity,Json.optString("Message"))

                        } else {
                            Toast.makeText(this@RegisterActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()

                           // showToastMessage(this@RegisterActivity,Json.optString("Message"))

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun mGetIP() {
        val params = HashMap<String, String>()

        servicerequestCall?.makeStringRequest1(URLConstants.ipurl,
            this@RegisterActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("GETIP-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        ipAddress= Json.optString("ip")
                        println("ip=="+ipAddress)


                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }


    override fun onResume() {
        super.onResume()
        updatetoolbar("Register",false)

    }

}
