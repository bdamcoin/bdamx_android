package com.application.bdamx.paperkey

import android.annotation.SuppressLint
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_paperkeypattern.*

import android.content.Intent

import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.CustomViewPagerAdapter
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.model.paperkeyModel

import java.util.ArrayList


class Paperkeypattern : BaseActivity() {
    private var mAdapter: CustomViewPagerAdapter? = null
    internal var paperList: MutableList<paperkeyModel> = ArrayList()
    private var viewPager: ViewPager? = null
    private var next_btn: Button? = null
    private var previous_btn: Button? = null
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null
    private val TAG = Paperkeypattern::class.java.simpleName


    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {

            // changing the next button text 'NEXT' / 'GOT IT'
            println("pos===$position")
            if (position == 0) {
                // last page. make button text to GOT IT
                previous_btn!!.visibility = View.GONE
                next_btn!!.visibility = View.VISIBLE
                //btnSkip.setVisibility(View.GONE);
            } else if (position == paperList.size - 1) {
                //next_btn!!.visibility = View.GONE
                previous_btn!!.visibility = View.VISIBLE

                next_btn?.setOnClickListener {
                    val intent = Intent(this@Paperkeypattern, KeysubmitActivity::class.java)
                    startActivity(intent)
                }

            } else {
                // still pages are left
                previous_btn!!.visibility = View.VISIBLE
                next_btn!!.visibility = View.VISIBLE
                // btnSkip.setVisibility(View.VISIBLE);
            }

        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paperkeypattern)

        servicerequestCall = ServiceRequestCall(this)
        urlConstants = URLConstants(this)

        viewPager = findViewById<View>(R.id.hot_deal_view_pager) as ViewPager
        next_btn = findViewById<View>(R.id.next_btn) as Button
        previous_btn = findViewById<View>(R.id.previous_btn) as Button



        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)
        next_btn!!.setOnClickListener {
            val current = viewPager!!.currentItem + 1
            if (current < paperList.size) {
                viewPager!!.currentItem = current
                previous_btn!!.visibility = View.VISIBLE
            }

            if (current == paperList.size - 1) {
                //next_btn!!.visibility = View.INVISIBLE
                previous_btn!!.visibility = View.VISIBLE

            }
        }


        previous_btn!!.setOnClickListener {
            val current = viewPager!!.currentItem - 1
            if (current < paperList.size) {
                viewPager!!.currentItem = current
                previous_btn!!.visibility = View.VISIBLE
                next_btn!!.visibility = View.VISIBLE
            }

            if (current == 0) {
                next_btn!!.visibility = View.GONE
            }
        }

        close_button.setOnClickListener {
            finish()
        }

        paperList.add(paperkeyModel("check1"))
        paperList.add(paperkeyModel("check2"))
        paperList.add(paperkeyModel("check3"))
        paperList.add(paperkeyModel("check4"))
        paperList.add(paperkeyModel("check5"))

        mAdapter = CustomViewPagerAdapter(this@Paperkeypattern, paperList)
        viewPager!!.adapter = mAdapter
        //mGetRandomkey()
    }


/*
    private fun mGetRandomkey() {

        val jsonObject = JSONObject()
        try {

            jsonObject.put("user_id",urlConstants!!.userid)

        } catch (e: JSONException) {
            Log.e(TAG, "error" + e.toString())
        }

        servicerequestCall?.customObjectRequest(URLConstants.random_keywords, this, jsonObject, object : DataCallback {
            override fun onSuccess(result: JSONObject) {
                Log.e("RANDOMKEY=",result.toString())

                var status: String? = null
                try {
                    status = result.optString("status")

                    val `randomkey` = result.getJSONArray("random_values")
                    for (i in 0..(randomkey.length() - 1)) {
                        val `a` = randomkey.getJSONObject(i)
                        paperList.add(ModelData.paperkey(`a`.optString("key")))

//                        countryIdList.add(a.optString("id"))
                    }

                    mAdapter = CustomViewPagerAdapter(this@Paperkeypattern, paperList)
                    viewPager!!.adapter = mAdapter

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
*/



}

