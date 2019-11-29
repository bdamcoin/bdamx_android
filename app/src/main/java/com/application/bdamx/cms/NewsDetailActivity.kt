package com.application.bdamx.cms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.bdamx.R
import com.application.bdamx.login.BaseActivity
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        var I=intent
        var title=I.getStringExtra("title")
        var desc=I.getStringExtra("desc")

        if(title!=null&&!title.equals("")&&!title.equals("null")){
            mTitle.setText(title)
        }

        if(desc!=null&&!desc.equals("")&&!desc.equals("null")){
            mContent.setText(desc)
        }

    }

    override fun onResume() {
        super.onResume()
        updatetoolbar("NewsDetail",false)

    }
}
