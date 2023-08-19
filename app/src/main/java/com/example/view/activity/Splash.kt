package com.example.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commereceapplication.R
import com.example.model.preferences.SharedPreference
import com.example.presenter.SplashContract
import com.example.presenter.SplashPresenter

class Splash : AppCompatActivity(), SplashContract.SplashView {
    private lateinit var splashPresenter: SplashContract.SplashPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashPresenter = SplashPresenter(this, SharedPreference(applicationContext))
        splashPresenter.onViewCreated()
    }

    override fun navigateToRegistration() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun navigateToIntro() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}