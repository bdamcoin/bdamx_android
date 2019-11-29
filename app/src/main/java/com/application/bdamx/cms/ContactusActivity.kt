package com.application.bdamx.cms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.login.RegisterActivity
import com.application.bdamx.utils.DataCallback1
import kotlinx.android.synthetic.main.activity_contactus.*
import org.json.JSONException
import org.json.JSONObject

class ContactusActivity : BaseActivity() {

    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    private val TAG = ContactusActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactus)

        servicerequestCall = ServiceRequestCall(this)
        urlConstants = URLConstants(this)

        mSubmitBtn.setOnClickListener {
            if(mNameEditTxt.text.toString().length==0){
                Toast.makeText(this@ContactusActivity, "Please fill name", Toast.LENGTH_SHORT).show()

            }else if(mEmailEditTxt.text.toString().length==0){
                Toast.makeText(this@ContactusActivity, "Please fill email ID", Toast.LENGTH_SHORT).show()

            }
            else if (!emailValidator(mEmailEditTxt.text.toString())) {
                Toast.makeText(this@ContactusActivity, "Please fill valid email ID", Toast.LENGTH_LONG).show()
            }
            else if(mSubjectEditTxt.text.toString().length==0){
                Toast.makeText(this@ContactusActivity, "Please fill subject", Toast.LENGTH_SHORT).show()

            }else if(mMsgEditTxt.text.toString().length==0){
                Toast.makeText(this@ContactusActivity, "Please fill message", Toast.LENGTH_SHORT).show()

            }else{
                makeContactus()
            }
        }

        getsettings()
    }

    private fun getsettings() {
        val params = HashMap<String, String>()

        servicerequestCall?.makeStringRequestGet(
            URLConstants.getsetttings,
            this@ContactusActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("Settings-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        status = Json.optString("status")
                        if (status.equals("true")) {
                            var data=Json.optJSONObject("data")

                            //nCompanyname.setText(Html.fromHtml(Json.optString("contact_address")))
                            nCompanyname.setText(data.optString("contact_address"))
                            phone_txt.setText("Phone: "+data.optString("contact_phone"))
                            email_txt.setText("Email: "+data.optString("contact_mail"))
                        } else {

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun makeContactus() {
        val params = HashMap<String, String>()
        try {
            params.put("name", mNameEditTxt.text.toString())
            params.put("email", mEmailEditTxt.text.toString())
            params.put("subject", mSubjectEditTxt.text.toString())
            params.put("message", mMsgEditTxt.text.toString())

        } catch (e: JSONException) {
            Log.e(TAG, "error" + e.toString())
        }

        servicerequestCall?.makeStringRequest1(URLConstants.contactus,
            this@ContactusActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("Contacts--", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("success")
                        if (status.equals("true")) {
                            Toast.makeText(this@ContactusActivity,"Your query has been placed admin will reply mail to your query", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@ContactusActivity,"Error Ocurred.Please Try Again", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
    override fun onResume() {
        super.onResume()
        updatetoolbar("Contact Us",false)

    }

}
