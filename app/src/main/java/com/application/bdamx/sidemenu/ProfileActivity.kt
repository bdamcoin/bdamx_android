package com.application.bdamx.sidemenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.bdamx.R
import com.application.bdamx.login.BaseActivity

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onResume() {
        super.onResume()
        updatetoolbar(getString(R.string.profile),false)

    }
}
