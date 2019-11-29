package com.application.bdamx.Introslider

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.application.bdamx.MainActivity
import com.application.bdamx.R
import com.application.bdamx.login.HomeActivity

import kotlinx.android.synthetic.main.activity_introslider.*


class IntrosliderActivity : AppCompatActivity() {
    private var view_pager: ViewPager? = null
    private var mBtnNext: Button? = null
    private var mBtnSkip: TextView? = null
    private var layouts: IntArray? = null
    //var web: Webservice? = null
    //private var prefManager: PrefManager? = null
    private lateinit var dots: Array<TextView?>
    private var layoutDots: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //web = Webservice(this)


        /*  if (web?.user_pin.equals("logged")) {
              mLaunchHomeScreen()
              finish()

          }*/
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_introslider)

        initVariables()
    }


    private fun initVariables() {
        //web!!.user_pin="logged"
        view_pager = findViewById(R.id.view_pager) as ViewPager
        layoutDots = findViewById(R.id.layoutDots) as LinearLayout
        mBtnNext = findViewById(R.id.button_signup) as Button
        mBtnSkip = findViewById(R.id.button_skip) as TextView

        layouts = intArrayOf(R.layout.activity_slider_one, R.layout.activity_slider_two, R.layout.activity_slider_three)

        // adding bottom dots
        addBottomDots(0);

        view_pager!!.setAdapter(MyViewPagerAdapter(getSupportFragmentManager(), layouts, this))
        // view_pager!!.addOnPageChangeListener(viewPagerPageChangeListener)
        view_pager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {


            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                // adding bottom dots
                addBottomDots(position);


                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == layouts!!.size - 1) {
                    // last page. make button text to GOT IT
                    //button_skip.visibility=View.INVISIBLE
                    button_skip.text="Get started"

                } else {
                    button_skip.text="Skip"
                    button_skip.visibility=View.VISIBLE
//                    val slide_up = AnimationUtils.loadAnimation(applicationContext,
//                            R.anim.bounce)
//                    val interpolator = MyBounceInterpolator(0.2, 20.0)
//                    slide_up.setInterpolator(interpolator)
                    //view_pager!!.startAnimation(slide_up)
                }
            }

        })

        //Action For SkipButton
        mBtnSkip!!.setOnClickListener {
            val I = Intent(this, MainActivity::class.java)
            startActivity(I)
            finish()
        }

        //Action For Next Button
        mBtnNext!!.setOnClickListener {

//            var current: Int = view_pager!!.currentItem + 1
//            if (current < layouts!!.size) {
//                // move to next screen
//
//            } else {
//                mLaunchHomeScreen();
//                finish()
//            }
        }


    }

    private fun addBottomDots(currentpage: Int) {
        dots = arrayOfNulls(layouts!!.size)
        var colorsActive: IntArray = resources.getIntArray(R.array.array_dot_active)
        var colorsInactive: IntArray = resources.getIntArray(R.array.array_dot_inactive)
        layoutDots!!.removeAllViews()

        //For Loop
        for (i in dots!!.indices) {
            dots!![i] = TextView(this)
            dots[i]!!.setText(Html.fromHtml("&#8226;"));
            dots!![i]!!.textSize = 35f;
            dots!![i]!!.setTextColor(colorsInactive[currentpage]);
            layoutDots!!.addView(dots!![i]);
        }
        if (dots!!.size > 0) {
            dots!![currentpage]!!.setTextColor(colorsActive[currentpage])
        }
    }
}

internal class MyBounceInterpolator(amplitude: Double, frequency: Double) : android.view.animation.Interpolator {
    private var mAmplitude = 1.0
    private var mFrequency = 10.0

    init {
        mAmplitude = amplitude
        mFrequency = frequency
    }

    override fun getInterpolation(time: Float): Float {
        return (-1.0 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrequency * time) + 1).toFloat()
    }
}
private operator fun Boolean.invoke(b: Boolean) {

}
