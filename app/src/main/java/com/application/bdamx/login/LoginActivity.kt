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
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        servicerequestCall = ServiceRequestCall(this)
        urlConstants = URLConstants(this)

        mSignupbtn.setOnClickListener {
            var I= Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(I)
        }

        mLoginbtn.setOnClickListener {
            if(mEmailEdittext.text.toString().length==0){
                Toast.makeText(this@LoginActivity, "Please fill email ID", Toast.LENGTH_SHORT).show()
            }else if (!emailValidator(mEmailEdittext.text.toString())) {
                Toast.makeText(this@LoginActivity, "Please fill valid email ID", Toast.LENGTH_LONG).show()
            }else if (mOtpEdittext.text.toString().length==0) {
                Toast.makeText(this@LoginActivity, "Please fill OTP", Toast.LENGTH_SHORT).show()
            }
            else{
                makeLogin()
            }
        }

        mSendotpbtn.setOnClickListener {
            println("checkkk==="+"checkkkkk");
            if(mEmailEdittext.text.toString().length==0){
                Toast.makeText(this@LoginActivity, "Please fill email ID", Toast.LENGTH_SHORT).show()
            }else if (!emailValidator(mEmailEdittext.text.toString())) {
                Toast.makeText(this@LoginActivity, "Please fill valid email ID", Toast.LENGTH_LONG).show()
            }else{
                mGetotp()
            }
        }

    }

    private fun mGetotp() {
        val params = HashMap<String, String>()
        try {
            params.put("email", mEmailEdittext.text.toString())

        } catch (e: JSONException) {
            Log.e(TAG, "error" + e.toString())
        }

        servicerequestCall?.makeStringRequest1(
            URLConstants.LogOTPSend,
            this@LoginActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("GETOTP-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        println("status==="+status)
                        if (status.equals("true")) {
                            Toast.makeText(this@LoginActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()

                           // showToastMessage(this@RegisterActivity,Json.optString("Message"))

                        } else {
                            Toast.makeText(this@LoginActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()

                            // showToastMessage(this@RegisterActivity,Json.optString("Message"))

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }


    private fun makeLogin() {
        val params = HashMap<String, String>()
        try {
            params.put("email", mEmailEdittext.text.toString())
            params.put("otp", mOtpEdittext.text.toString())

        } catch (e: JSONException) {
            Log.e(TAG, "error" + e.toString())
        }

        servicerequestCall?.makeStringRequest1(URLConstants.login,
            this@LoginActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("Login-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        status = Json.optString("status")
                        if (status.equals("true")) {
                            Toast.makeText(this@LoginActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()
                            urlConstants!!.tokenaddrs=Json.optString("originaladdress")
                            var I= Intent(this@LoginActivity,HomeActivity::class.java)
                            startActivity(I)

                        } else {
                            Toast.makeText(this@LoginActivity,Json.optString("Message"), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }



    override fun onResume() {
        super.onResume()
        updatetoolbar("Login",false)
    }
}
