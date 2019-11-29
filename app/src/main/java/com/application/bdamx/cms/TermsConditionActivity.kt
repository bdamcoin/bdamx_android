package com.application.bdamx.cms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.model.newsModel
import com.application.bdamx.utils.DataCallback1
import kotlinx.android.synthetic.main.activity_terms_condition.*
import org.json.JSONObject

class TermsConditionActivity : BaseActivity() {
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    var mType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_condition)
        servicerequestCall = ServiceRequestCall(this)
        urlConstants = URLConstants(this)

        var I=intent
        var cms=I.getStringExtra("cms")

        if(cms!=null&&!cms.equals("")&&!cms.equals("null")){
            if(cms.equals("security")){
                mType="security"
                updatetoolbar("Security Center",false)
            }else if(cms.equals("aboutus")){
                mType="aboutus"
                updatetoolbar("About Us",false)
            }
        }else{
            mType="terms"
            updatetoolbar("Terms and Condition",false)
        }

        mGetcms()
    }

    private fun mGetcms() {
        val params = HashMap<String, String>()
        try {
            params.put("page_name",mType!!)
        }catch (e:Exception){

        }

        servicerequestCall?.makeStringRequestGet(
            URLConstants.cms,
            this@TermsConditionActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("getcms-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if(status.equals("true")){

                            var data=Json.optJSONObject("data")
                            var page_content=data.optString("page_content")
                            mContent.setText(Html.fromHtml(page_content))

                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }


    override fun onResume() {
        super.onResume()
        //updatetoolbar("Terms and Condition",false)

    }
}
