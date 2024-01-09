package com.example.demo.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.R
import com.example.demo.databinding.ActivitySplashScreenBinding
import com.example.demo.ui.main.MainActivity
import com.example.demo.utilities.Constants.Companion.ANIMATION_LOADING
import com.example.demo.utilities.Constants.Companion.SPLASH_LOADING
import com.example.demo.utilities.Utilities.Companion.loadAnimationTopIn
import com.example.demo.utilities.Utilities.Companion.showToast
import com.example.demo.utilities.Utilities.Companion.transitionOpen

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    var backPress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        setAnimations()
        runInBackground()
    }

    private fun setAnimations(){
        val rotate = RotateAnimation(
            0F, 6F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )

        rotate.duration = 900
        rotate.repeatCount = Animation.INFINITE
        binding.imageView.startAnimation(rotate)
        binding.cardView.startAnimation(loadAnimationTopIn(this, ANIMATION_LOADING.toLong()))
    }

    private fun runInBackground() {
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(SPLASH_LOADING.toLong())
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    transitionOpen(this@SplashScreenActivity)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            backPress++
            if (backPress == 1) {
                showToast(this@SplashScreenActivity, getString(R.string.press_again_to_exit))
            } else {
                finishAffinity()
            }
        }
    }
}