package com.application.bdamx.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.adapter.HomeCurrencyAdapter
import com.application.bdamx.adapter.NotificationAdapter
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.model.currencyModel
import com.application.bdamx.model.notificationModel
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivity() {

    var mNotificationAdapter : NotificationAdapter? = null
    var notifyList: MutableList<notificationModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        notifyList.add(notificationModel("",""))
        notifyList.add(notificationModel("",""))
        notifyList.add(notificationModel("",""))
        notifyList.add(notificationModel("",""))
        notifyList.add(notificationModel("",""))
        notifyList.add(notificationModel("",""))
        notifyList.add(notificationModel("",""))
        notifyList.add(notificationModel("",""))

        notification_recycle.layoutManager = LinearLayoutManager(this@NotificationActivity, RecyclerView.VERTICAL, false)
        mNotificationAdapter = NotificationAdapter(notifyList, this@NotificationActivity, 1,this@NotificationActivity)
        notification_recycle.adapter = mNotificationAdapter
    }

    override fun onResume() {
        super.onResume()
        updatetoolbar(getString(R.string.notification),false)

    }
}
