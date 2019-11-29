package com.application.bdamx.login

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.bdamx.MainActivity
import com.application.bdamx.R
import com.application.bdamx.Utils.URLConstants
import com.application.bdamx.adapter.NavsliderAdapter
import com.application.bdamx.adapter.NavslidertwoAdapter
import com.application.bdamx.login.ui.*
import com.application.bdamx.model.navigationModel
import com.application.bdamx.notification.NotificationActivity
import com.application.bdamx.sidemenu.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.popup_common.*
import kotlinx.android.synthetic.main.toolbar_header.*

class HomeActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var menulist:MutableList<navigationModel>?= mutableListOf()
    var menulist2:MutableList<navigationModel>?= mutableListOf()
    var navigationAdapter:NavsliderAdapter?=null
    var navigationAdapter2:NavslidertwoAdapter?=null
    var urlConstants: URLConstants? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_home -> {
                val fragment = HomeFragment()
                updateDisplay(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_portfilo -> {
                val fragment = WalletFragment()
                updateDisplay(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                val fragment = AccountFragment()
                updateDisplay(fragment)
                return@OnNavigationItemSelectedListener true
            }
           /* R.id.navigation_news -> {
                val fragment = NewsFragment()
                updateDisplay(fragment)
                return@OnNavigationItemSelectedListener true
            }*/
            R.id.navigation_settings -> {
                val fragment = SettingsFragment()
                updateDisplay(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        urlConstants = URLConstants(this)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        menu_img.setOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }

        notification.setOnClickListener {
            var I=Intent(this@HomeActivity,NotificationActivity::class.java)
            startActivity(I)
        }

        menulist!!.add(navigationModel(1,R.drawable.ic_dashboard,"Dashboard"))
        menulist!!.add(navigationModel(2,R.drawable.ic_market,"Market"))
        menulist!!.add(navigationModel(3,R.drawable.ic_coin,"Trade"))
        //menulist!!.add(navigationModel(4,R.drawable.ic_wallet,"Wallet"))

        menulist2!!.add(navigationModel(1,R.drawable.ic_invite_friends,"History"))
        //menulist2!!.add(navigationModel(2,R.drawable.ic_profile,"Profile"))
        menulist2!!.add(navigationModel(3,R.drawable.ic_settings,"Settings"))
        menulist2!!.add(navigationModel(4,R.drawable.ic_logout,"Logout"))

        navrecyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        navigationAdapter = NavsliderAdapter(this,1,menulist!!,object :NavsliderAdapter.OnItemClickListener{
            override fun onItemClick(item: navigationModel) {
                drawer_layout.closeDrawer(GravityCompat.START)
                println("texttt==="+item.id)
                pageselected(item.id)
            }

        })
        navrecyclerView.adapter=navigationAdapter

        navrecyclerView2.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        navigationAdapter2 = NavslidertwoAdapter(this,1,menulist2!!,object : NavslidertwoAdapter.OnItemClickListener{
            override fun onItemClick(itemmenu: navigationModel) {
                drawer_layout.closeDrawer(GravityCompat.START)
                pageselectednew(itemmenu.id)
            }

        })
        navrecyclerView2.adapter=navigationAdapter2

        val fragment = HomeFragment()
        updateDisplay(fragment)
    }

    fun pageselected(id:Int) {
        val g=Bundle()

        if(id==1)
        {
            g.putString("page","1")
            var I=Intent(this@HomeActivity,HomeActivity::class.java)
            startActivity(I)

        }else if(id==2)
        {
            g.putString("page","1")
            var I=Intent(this@HomeActivity,DataMarketActivity::class.java)
            startActivity(I)
        }else if(id==3)
        {
            g.putString("page","1")
            var I=Intent(this@HomeActivity,TradeActivity::class.java)
            startActivity(I)

        }else if(id==4)
        {
            g.putString("page","1")
            var I=Intent(this@HomeActivity,WalletActivity::class.java)
            startActivity(I)
        }
    }

    fun pageselectednew(id:Int) {
        val g=Bundle()

        if(id==1)
        {
            g.putString("page","1")
            var I=Intent(this@HomeActivity,HistoryActivity::class.java)
            startActivity(I)

        }else if(id==2)
        {
            g.putString("page","1")
            var I=Intent(this@HomeActivity,ProfileActivity::class.java)
            startActivity(I)
        }
        else if(id==4)
        {
            g.putString("page","4")
            logout()
        }
    }


    private fun updateDisplayWithBackstack(fr: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_place, fr).addToBackStack(null).commit()
    }

    private fun updateDisplay(fr: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_place, fr).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            Log.e("onBackPressed: ", "" + supportFragmentManager.backStackEntryCount)
            if (supportFragmentManager.backStackEntryCount > 0) run {
                super.onBackPressed()
            } else {
               //mShowExit()
            }

        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
              /*  val fragment = HomeFragment()
                updateDisplay(fragment)*/
            }


        }

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }



    fun setMenuIcon(contentDesc: String) {
        txttitle.setText(contentDesc)
    }

    fun logout() {
        var v_dialog = Dialog(this)
        v_dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        v_dialog?.setCancelable(false)
        v_dialog?.setContentView(R.layout.popup_common)

        v_dialog!!.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        v_dialog?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        v_dialog?.window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        v_dialog?.show()
        v_dialog?.txtmsg.setText("Are you sure you want to end the session? ")
        v_dialog?.mtitle_pop.setText("Logout")
        //v_dialog?.mImg.setImageResource(R.drawable.logout)


        v_dialog?.ok?.setOnClickListener {
            v_dialog?.dismiss()
            urlConstants?.clear()
            val intent = Intent(this@HomeActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent)
            finish()

        }

        v_dialog?.cancel?.setOnClickListener {
            v_dialog?.dismiss()
        }
    }


    override fun onResume() {
        super.onResume()
    }


}
