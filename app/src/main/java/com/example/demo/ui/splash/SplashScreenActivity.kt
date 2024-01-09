package com.example.demo.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivitySplashScreenBinding
import com.example.demo.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    var backPress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runInBackground()
    }

    private fun runInBackground() {
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(5000)
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}