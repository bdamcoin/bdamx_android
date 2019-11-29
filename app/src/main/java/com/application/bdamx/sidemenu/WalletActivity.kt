package com.application.bdamx.sidemenu

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.Utils.ServiceRequestCall
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.NotificationAdapter
import com.application.bdamx.adapter.WalletAdapter
import com.application.bdamx.login.BaseActivity
import com.application.bdamx.model.datamarketModel
import com.application.bdamx.model.notificationModel
import com.application.bdamx.model.walletModel
import com.application.bdamx.utils.DataCallback1
import kotlinx.android.synthetic.main.activity_notification.*
import org.json.JSONObject

class WalletActivity : BaseActivity() {

    var mWalletAdapter : WalletAdapter? = null
    var walletList: MutableList<walletModel> = mutableListOf()
    var servicerequestCall: ServiceRequestCall? = null
    var urlConstants: URLConstants? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        servicerequestCall = ServiceRequestCall(this@WalletActivity)
        urlConstants = URLConstants(this@WalletActivity)


        notification_recycle.layoutManager = LinearLayoutManager(this@WalletActivity, RecyclerView.VERTICAL, false)
        mWalletAdapter = WalletAdapter(walletList, this@WalletActivity, 1)
        notification_recycle.adapter = mWalletAdapter

        //mGetWallet()
    }




    override fun onResume() {
        super.onResume()
        updatetoolbar(getString(R.string.wallet),false)

    }
}
