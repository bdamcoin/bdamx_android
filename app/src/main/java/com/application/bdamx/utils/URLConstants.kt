package com.application.bdamx.Utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * Created by devel-73 on 27/10/17.
 */

class URLConstants(var context: Context) {

    var userid: String


        get() {
            val sharedPref = context.getSharedPreferences(USERPREF, MODE_PRIVATE)
            return sharedPref.getString(USERPREF, "")!!

        }
        set(userid) {
            val sharedPref = context.getSharedPreferences(USERPREF,MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(USERPREF, userid)
            editor.commit()
        }


    var mobileph: String
        get() {
            val sharedPref = context.getSharedPreferences(USERPIN, MODE_PRIVATE)
            return sharedPref.getString(USERPIN, "")!!

        }
        set(pin) {
            val sharedPref = context.getSharedPreferences(USERPIN,MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(USERPIN, pin)
            editor.commit()
        }


    var pinstatus: String
        get() {
            val sharedPref = context.getSharedPreferences(USERPINSTATUS, MODE_PRIVATE)
            return sharedPref.getString(USERPINSTATUS, "")!!

        }
        set(pinstatus) {
            val sharedPref = context.getSharedPreferences(USERPINSTATUS,MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(USERPINSTATUS, pinstatus)
            editor.commit()
        }



    var email: String
        get() {
            val sharedPref = context.getSharedPreferences(EMAIL, MODE_PRIVATE)
            return sharedPref.getString(EMAIL, "")!!

        }
        set(email) {
            val sharedPref = context.getSharedPreferences(EMAIL, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(EMAIL, email)
            editor.commit()
        }

    var profile_picture: String
        get(){
            val sharedPref = context.getSharedPreferences(PROFILE_IMAGE,MODE_PRIVATE)
            return sharedPref.getString(PROFILE_IMAGE, "")!!
        }
        set(profile_picture) {
            val sharedPref = context.getSharedPreferences(PROFILE_IMAGE,MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(PROFILE_IMAGE, profile_picture)
            editor.commit()
        }
    var username: String
        get(){
            val sharedPref = context.getSharedPreferences(NAME,MODE_PRIVATE)
            return sharedPref.getString(NAME, "")!!
        }
        set(username) {
            val sharedPref = context.getSharedPreferences(NAME, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(NAME, username)
            editor.commit()
        }

    var user_pin: String
        get(){
            val sharedPref = context.getSharedPreferences(USER_PIN, MODE_PRIVATE)
            return sharedPref.getString(USER_PIN, "")!!
        }
        set(user_pin) {
            val sharedPref = context.getSharedPreferences(USER_PIN, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(USER_PIN, user_pin)
            editor.commit()
        }

    var user_country: String
        get(){
            val sharedPref = context.getSharedPreferences(USER_PIN, MODE_PRIVATE)
            return sharedPref.getString(USER_COUNTRY, "")!!
        }
        set(user_country) {
            val sharedPref = context.getSharedPreferences(USER_PIN, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(USER_COUNTRY, user_country)
            editor.commit()
        }

    var isLogin: Boolean
        get(){
            val sharedPref = context.getSharedPreferences(IS_LOGIN, MODE_PRIVATE)
            return sharedPref.getBoolean(IS_LOGIN, false)

        }
        set(isLogin)
        {
            val sharedPref = context.getSharedPreferences(IS_LOGIN, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean(IS_LOGIN, isLogin)
            editor.commit()
        }

    var appLock: Boolean
        get(){
            val sharedPref = context.getSharedPreferences(APPLOCK, MODE_PRIVATE)
            return sharedPref.getBoolean(APPLOCK, false)

        }
        set(appLock)
        {
            val sharedPref = context.getSharedPreferences(APPLOCK, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean(APPLOCK, appLock)
            editor.commit()
        }

    var tokenaddrs: String
        get(){
            val sharedPref = context.getSharedPreferences(TOKEN, MODE_PRIVATE)
            return sharedPref.getString(TOKEN, "")!!

        }
        set(tokenaddrs)
        {
            val sharedPref = context.getSharedPreferences(TOKEN, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(TOKEN, tokenaddrs)
            editor.commit()
        }

    var fingerprint: String
        get(){
            val sharedPref = context.getSharedPreferences(FINGER_PRINT, MODE_PRIVATE)
            return sharedPref.getString(FINGER_PRINT, "")!!
        }
        set(fingerprint) {
            val sharedPref = context.getSharedPreferences(FINGER_PRINT, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(FINGER_PRINT, fingerprint)
            editor.commit()
        }

    var nativecurrency: String
        get(){
            val sharedPref = context.getSharedPreferences(NATIVECURRENCY, MODE_PRIVATE)
            return sharedPref.getString(NATIVECURRENCY, "")!!
        }
        set(nativecurrency) {
            val sharedPref = context.getSharedPreferences(NATIVECURRENCY, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(NATIVECURRENCY, nativecurrency)
            editor.commit()
        }

    var code: String
        get(){
            val sharedPref = context.getSharedPreferences(CODE, MODE_PRIVATE)
            return sharedPref.getString(CODE, "")!!
        }
        set(code) {
            val sharedPref = context.getSharedPreferences(CODE, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(CODE, code)
            editor.commit()
        }

    var Tfacode: String
        get(){
            val sharedPref = context.getSharedPreferences(TFACODE, MODE_PRIVATE)
            return sharedPref.getString(TFACODE, "")!!
        }
        set(Tfacode) {
            val sharedPref = context.getSharedPreferences(TFACODE, MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(TFACODE, Tfacode)
            editor.commit()
        }

    fun setApp_runFirst(App_runFirst: String) {
        val sharedPref = context.getSharedPreferences(FIRSTTIMELAUNCH, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.remove(FIRSTTIMELAUNCH)
        editor.putString(FIRSTTIMELAUNCH, App_runFirst)
        editor.commit()
    }

    fun getApp_runFirst(): String {
        val sharedPref = context.getSharedPreferences(FIRSTTIMELAUNCH, MODE_PRIVATE)
        val App_runFirst = sharedPref.getString(FIRSTTIMELAUNCH, "FIRST")
        return App_runFirst!!
    }

    fun clear() {
        context.getSharedPreferences(USERPREF, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(IS_LOGIN, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(PROFILE_IMAGE, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(USER_PIN, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(APPLOCK, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(NAME, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(TFACODE, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(CODE, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(EMAIL, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(TOKEN, MODE_PRIVATE).edit().clear().commit()


    }

    fun clearall() {
        context.getSharedPreferences(APPLOCK_STATUS, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(APPLOCK,MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(USERPREF,MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(EMAIL, MODE_PRIVATE).edit().clear().commit()
        context.getSharedPreferences(NAME, MODE_PRIVATE).edit().clear().commit()

    }

    companion object {

        //var url = "https://bdamx_revampback.osiztechnologies.in:2096/"
        //var url = "https://bdnasudn.bdamx.net:8443/"
        var url = "http://bdamx_revampback.osiztechnologies.in:2095/"
        var https_status = "0"
        var android = "Android"


        var ipurl  ="https://jsonip.com/"
        var register  =url+"register"
        var login   =url+"logintosite"
        var getsetttings =url+"sitesetting/getsettings"
        var LogOTPSend =url+"LogOTPSend"
        var RegOTPSend =url+"RegOTPSend"
        var news =url+"news/getnews"
        var homemarket =url+"trade/PairsGet"
        var getcurrency =url+"trade/getCurrency"
        var cms =url+"cms/get_cms"
        var contactus =url+"contactus/add_contact"
        var withdraw =url+"BdAMx4fdex/btcwithdraw"
        var cancelorder =url+"trade/get_cancelled"


        //Socket Url

        //val CHAT_SERVER_URL = "http://192.168.4.12:2095"
        val CHAT_SERVER_URL = "http://bdamx_revampback.osiztechnologies.in:2095"



        val USERPIN = "pin"
        val USERPINSTATUS = "pinstatus"
        val USERPREF = "userid"
        val IS_LOGIN = "isLogin"
        val PROFILE_IMAGE = "profile"
        val USER_PIN = "userpin"
        val FINGER_PRINT = "fingerprint"
        val CODE = "code"
        val TFACODE = "Tfacode"
        val FIRSTTIMELAUNCH = "FIRST"
        val USER_COUNTRY = "usercountry"
        val NATIVECURRENCY = "nativecurrency"
        val TOKEN = "TOKEN"

        val APPLOCK = "applock"
        val APPLOCK_STATUS = "applock_status"
        val NAME = "name"
        val EMAIL = "email_id"
        var PREF_NAME: String = "centrex"
        val PASSCODE = "passcode"

    }

}
