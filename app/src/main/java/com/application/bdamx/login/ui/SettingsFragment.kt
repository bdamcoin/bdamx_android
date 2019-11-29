package com.application.bdamx.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.bdamx.R
import com.application.bdamx.cms.ContactusActivity
import com.application.bdamx.cms.NewsActivity
import com.application.bdamx.cms.TermsConditionActivity
import com.application.bdamx.login.HomeActivity
import com.dcwallet.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.view.*
import android.widget.CompoundButton



class SettingsFragment   : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_settings, container, false)


        mView!!.mTerms.setOnClickListener {
            var I= Intent(activity!!,TermsConditionActivity::class.java)
            activity!!.startActivity(I)
        }

        mView!!.mNews_layout.setOnClickListener {
            var I= Intent(activity!!,NewsActivity::class.java)
            activity!!.startActivity(I)
        }

        mView!!.mContact_layout.setOnClickListener {
            var I= Intent(activity!!, ContactusActivity::class.java)
            activity!!.startActivity(I)
        }


        mView!!.mSwitch_btn.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The toggle is enabled
            } else {
                // The toggle is disabled
            }
        })


        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).setMenuIcon("Settings")
    }


}