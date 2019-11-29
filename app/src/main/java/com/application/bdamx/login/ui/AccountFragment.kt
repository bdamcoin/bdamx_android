package com.application.bdamx.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.R
import com.application.bdamx.adapter.HomeCurrencyAdapter
import com.application.bdamx.adapter.HomeMarketAdapter
import com.application.bdamx.cms.TermsConditionActivity
import com.application.bdamx.login.HomeActivity
import com.application.bdamx.model.currencyModel
import com.application.bdamx.sidemenu.TradeCommonActivity
import com.dcwallet.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class AccountFragment  : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_account, container, false)

        mView!!.security_layout.setOnClickListener {
            var I=Intent(activity!!,TermsConditionActivity::class.java)
            I.putExtra("cms","security")
            startActivity(I)
        }

        mView!!.aboutus_layout.setOnClickListener {
            var I=Intent(activity!!,TermsConditionActivity::class.java)
            I.putExtra("cms","aboutus")
            startActivity(I)
        }

        mView!!.order_layout.setOnClickListener {
            var I=Intent(activity!!,TradeCommonActivity::class.java)
            startActivity(I)
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).setMenuIcon("Account")
    }


}