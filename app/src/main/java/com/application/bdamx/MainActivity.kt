package com.application.bdamx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.login.LoginActivity
import com.application.bdamx.login.RegisterActivity
import com.application.bdamx.paperkey.Paperkeypattern
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var loginCurrn:String?="BTC"
    var urlConstants: URLConstants? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        urlConstants = URLConstants(this)

        Nextbtn.setOnClickListener {
            urlConstants?.nativecurrency=loginCurrn!!

            if(loginCurrn!=null&&!loginCurrn.equals("")&&!loginCurrn.equals("null")&&loginCurrn.equals("BTC")){
                var I=Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(I)
            }else{
                var I=Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(I)
            }

        }

        mEth_layout.setOnClickListener {
            loginCurrn ="ETH"
            mEth_layout.setCardBackgroundColor(resources.getColor(R.color.Colorbg))
            mBtc_layout.setCardBackgroundColor(resources.getColor(R.color.Color1))
        }

        mBtc_layout.setOnClickListener {
            loginCurrn="BTC"
            mEth_layout.setCardBackgroundColor(resources.getColor(R.color.Color1))
            mBtc_layout.setCardBackgroundColor(resources.getColor(R.color.Colorbg))
        }
    }
}
