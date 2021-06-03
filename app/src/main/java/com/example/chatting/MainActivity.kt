package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnSend.setOnClickListener {
            print(binding.editText1.text)
        }
    }
}