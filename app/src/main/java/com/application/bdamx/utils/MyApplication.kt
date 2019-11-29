package com.application.bdamx.utils

import android.app.Application
import com.application.bdamx.Utils.URLConstants
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException


class MyApplication : Application() {

    var socket: Socket? = null
        private set

    init {
        try {

            val mOptions = IO.Options()
            mOptions.query = "join" + "3423"
            mOptions.forceNew = true
            socket = IO.socket(URLConstants.CHAT_SERVER_URL, mOptions)
            //            mSocket = IO.socket(Constants.CHAT_SERVER_URL);

        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

    }
}