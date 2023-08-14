package com.example.e_commereceapplication.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commereceapplication.R

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = getSharedPreferences("intro", Context.MODE_PRIVATE)

        Handler().postDelayed({
            if (isFirstTimeUser()) {
                startIntroActivity()
            } else {
                startLoginActivity()
            }
        }, SPLASH_DELAY)
    }

    private fun isFirstTimeUser(): Boolean {
        return sharedPreferences.getBoolean("isFirstTime", true)
    }

    private fun startIntroActivity() {
        val intent = Intent(this@SplashActivity, IntroScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startLoginActivity() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SPLASH_DELAY = 2000L
    }
}

