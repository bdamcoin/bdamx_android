package com.application.bdamx.cms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.HomeMarketAdapter
import com.application.bdamx.adapter.NewsAdapter
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.model.currencyModel
import com.application.bdamx.model.newsModel
import com.application.bdamx.utils.DataCallback1
import kotlinx.android.synthetic.main.activity_news.*
import org.json.JSONException
import org.json.JSONObject

class NewsActivity : BaseActivity() {

    var mNewsAdapter : NewsAdapter? = null
    var newsList: MutableList<newsModel> = mutableListOf()
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        servicerequestCall = ServiceRequestCall(this)
        urlConstants = URLConstants(this)

        /*newsList.add(newsModel("","",""))
        newsList.add(newsModel("","",""))
        newsList.add(newsModel("","",""))
        newsList.add(newsModel("","",""))
        newsList.add(newsModel("","",""))
        newsList.add(newsModel("","",""))

        news_Recycle.layoutManager = LinearLayoutManager(this@NewsActivity, RecyclerView.HORIZONTAL, false)
        mNewsAdapter = NewsAdapter(newsList, this@NewsActivity, 1)
        news_Recycle.adapter = mNewsAdapter*/

        mGetnews()
    }

    private fun mGetnews() {
        val params = HashMap<String, String>()


        servicerequestCall?.makeStringRequestGet(
            URLConstants.news,
            this@NewsActivity, params, object : DataCallback1 {
                override fun onSuccess(result: Any) {
                    Log.e("getnews-", "" + result)
                    var status: String? = null
                    try {
                        val Json = JSONObject(result.toString().trim())
                        //val Json = JSONObject(result.toString().substring(result.toString().indexOf("{"), result.toString().lastIndexOf("}") + 1))
                        status = Json.optString("status")
                        if(status.equals("true")){
                            val data=Json.optJSONArray("data")
                            for (i in 0..(Json.length() - 1)) {
                                val newsData = data.getJSONObject(i)
                                newsList.add(
                                    newsModel(
                                        newsData.optString("modifieddate"),
                                        newsData.optString("description"),
                                        newsData.optString("content")
                                    )
                                )
                            }

                            updateUI(newsList)

                        }else{

                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    fun updateUI(list: List<newsModel>) {
        println("payList==" + list.size)

        if (list.size > 0) {
            println("checkk==" + list)
            news_Recycle.layoutManager = LinearLayoutManager(this@NewsActivity, RecyclerView.VERTICAL, false)
            mNewsAdapter = NewsAdapter(list, this@NewsActivity, 1)
            news_Recycle.adapter = mNewsAdapter

        } else {
           /* mFound_bank.visibility = View.VISIBLE
            bank_recycle.visibility = View.GONE*/
        }


    }



    override fun onResume() {
        super.onResume()
        updatetoolbar(getString(R.string.news),false)

    }
}
