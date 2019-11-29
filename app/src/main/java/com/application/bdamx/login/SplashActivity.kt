package com.application.bdamx.login

import android.app.KeyguardManager
import android.content.Intent
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.application.bdamx.Introslider.IntrosliderActivity
import com.application.bdamx.R
import com.application.bdamx.Utils.URLConstants

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 2000
    var userAddress:String?=null
    var urlConstants: URLConstants? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        urlConstants = URLConstants(this)
        userAddress= urlConstants!!.tokenaddrs
        //Sequent.origin(mSplash_layout).anim(this@SplashActivity, com.fujiyuu75.sequent.Animation.BOUNCE_IN).start()

        println("useraddress=="+userAddress)

        launchHomeScreen()
    }

    fun launchHomeScreen() {
        Handler().postDelayed({
            if (userAddress != null && userAddress != "null" && !userAddress!!.isEmpty()) {
                println("userid--$userAddress")
                val i = Intent(this@SplashActivity, HomeActivity::class.java)
                this@SplashActivity.startActivity(i)
                this@SplashActivity.finish()

            } else {
                val i = Intent(this@SplashActivity, IntrosliderActivity::class.java)
                this@SplashActivity.startActivity(i)
                this@SplashActivity.finish()
            }
        }, SPLASH_DISPLAY_LENGTH.toLong())

    }

}
