package com.application.bdamx.sidemenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.application.bdamx.R
import com.application.bdamx.utils.MyApplication
import io.socket.client.Socket
import android.widget.Toast

import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject


class SocketActivity : AppCompatActivity() {

    private var mSocket: Socket? = null
    private var isConnected = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)
        var app = MyApplication()
        mSocket=app.socket
        mSocket!!.on(Socket.EVENT_CONNECT, onConnect)
        mSocket!!.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket!!.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket!!.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)
        mSocket!!.on("emitPairsResponse", onNewMessage)
        mSocket!!.on("userResponse", onUserMessage)
        mSocket!!.on("createResponse", onCreatereposne)
        mSocket!!.on(Socket.EVENT_ERROR, onError)
        mSocket!!.connect()
    }

    private val onConnect = Emitter.Listener {
      runOnUiThread(Runnable {

            if (isConnected) {
                Toast.makeText(this,"Connected", Toast.LENGTH_LONG).show()
            }

          val obj = JSONObject()
          try {
            obj.put("amount", "2.00000000");
            obj.put("price", "0.10000000");
            obj.put("pair", "5ca3310f7e426d406a8f5");
            obj.put("ordertype", "buy");
            obj.put("type", "market");

          } catch (e: JSONException) {
              e.printStackTrace()
          }

          val message = obj.toString()
          Log.e("Param=", message)
          mSocket?.emit("createOrder", message);
            /* if(!isConnected) {
 //                        if(null!=mUsername)
                         mSocket.emit("join", "3423");
                     Toast.makeText(getActivity().getApplicationContext(),
                             R.string.connect, Toast.LENGTH_LONG).show();
                     isConnected = true;
                 }*/
        })
    }

    private val onDisconnect = Emitter.Listener {
        runOnUiThread(Runnable {
            isConnected = false
            Toast.makeText(this,"Disconnected", Toast.LENGTH_LONG).show()
        })
    }

    private val onConnectError = Emitter.Listener {
        runOnUiThread(Runnable {

            Toast.makeText(this,"Failed to Connect", Toast.LENGTH_LONG
            ).show()
        })
    }


    private val onNewMessage = object:Emitter.Listener {
        override fun call(vararg args:Any) {
            Log.e("Newmessage", "Newmessagecalled")
            val data = args[0] as JSONObject
            Log.e("data", "" + data)
            runOnUiThread(object:Runnable {
                public override fun run() {
                    Log.e("Newmessage", "Newmessagecalled")
                    val data = args[0] as JSONObject
                    Log.e("data", "" + data)

                }
            })
        }
    }

    private val onUserMessage = object:Emitter.Listener {
        override fun call(vararg args:Any) {
            Log.e("onUserMessage", "Newmessagecalled")
            val data = args[0] as JSONObject
            Log.e("historydata", "" + data)
            runOnUiThread(object:Runnable {
                public override fun run() {
                    Log.e("onUserMessage", "Newmessagecalled")
                    val data = args[0] as JSONObject
                    Log.e("UserMessagedata", "" + data)

                }
            })
        }
    }

    private val onCreatereposne = object:Emitter.Listener {
        override fun call(vararg args:Any) {
            Log.e("onCreateMessage", "Newmessagecalled")
            val data = args[0] as JSONObject
            Log.e("Createdata", "" + data)
            runOnUiThread(object:Runnable {
                public override fun run() {
                    Log.e("onCreateMessage", "Newmessagecalled")
                    val data = args[0] as JSONObject
                    Log.e("CreateMessagedata", "" + data)

                }
            })
        }
    }


    private val onError = Emitter.Listener { args ->
        Log.e("data", "" + args[0])
    }


}
