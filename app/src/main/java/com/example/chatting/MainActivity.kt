package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chatting.databinding.ActivityMainBinding
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
        lateinit var mSocket: Socket
    var username = "류호성"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {
            //IO.socket 메소드는 은 저 URL 을 토대로 클라이언트 객체를 Return 합니다.
            mSocket = IO.socket("https://trest.loca.lt/chat")
            Log.d("chatActivity socket", "connected")
        } catch (e: URISyntaxException) {
            Log.d("chatActivity socket", "failed")
        }
        mSocket.connect()
        mSocket.on(Socket.EVENT_CONNECT, onConnect)


        binding.btnSend.setOnClickListener {
            try {
                val obj = JSONObject()
                obj.put("name", username)
                obj.put("msg", binding.editText1.text.toString())
                obj.put("room", 1)
                Log.d("왜안돼",obj.toString())
                mSocket.emit("test", obj)
            }catch(e: JSONException) {
                e.printStackTrace();
            }
        }
    }

    val onConnect: Emitter.Listener = Emitter.Listener {
        //여기서 다시 "login" 이벤트를 서버쪽으로 username 과 함께 보냅니다.
        //서버 측에서는 이 username을 whoIsON Array 에 추가를 할 것입니다.
        mSocket.emit("login", username)
        Log.d("MainActivity", "Socket is connected with ${username}")
    }
}