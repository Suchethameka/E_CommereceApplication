package com.example.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.ActivityRegistrationBinding
import com.example.fragment.registrationfragments.LoginFragment

class Registration : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.registration_container, LoginFragment())
            .addToBackStack(null)
            .commit()

    }
    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}