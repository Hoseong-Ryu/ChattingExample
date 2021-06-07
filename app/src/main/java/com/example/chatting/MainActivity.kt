package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chatting.databinding.ActivityMainBinding
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {

    val gson: Gson = Gson()

    lateinit var mSocket: Socket
    private lateinit var binding: ActivityMainBinding

    var username = "류호성"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {
            //IO.socket 메소드는 은 저 URL 을 토대로 클라이언트 객체를 Return 합니다.
            mSocket = IO.socket("https://trest.loca.lt")
            Log.d("chatActivity socket", "connected")
        } catch (e: URISyntaxException) {
            Log.d("chatActivity socket", "failed")
        }
        mSocket.connect()

        binding.btnSend.setOnClickListener {

            val obj = JSONObject()
            obj.put("name", username)
            obj.put("msg", binding.editText1.text.toString())
            obj.put("room", 1)
            Log.d("왜안돼",obj.toString())
            mSocket.emit("test", obj)

        }
    }

}