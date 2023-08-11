package com.example.e_commereceapplication



import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.e_commereceapplication.databinding.ActivityIntroScreensBinding
import com.example.e_commereceapplication.fragments.AppIntroDemoFragment
import com.example.e_commereceapplication.fragments.CartDemoFragment
import com.example.e_commereceapplication.fragments.CheckOutDemoFragment


class IntroScreensActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroScreensBinding
    private lateinit var introPagerAdapter: IntroPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)

        introPagerAdapter = IntroPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = introPagerAdapter

        val sharedPreferences: SharedPreferences = getSharedPreferences("intro", MODE_PRIVATE)
        val isFirstTime: Boolean = sharedPreferences.getBoolean("isFirstTime", true)

        if (!isFirstTime) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isFirstTime", false)
        editor.apply()
    }

    inner class IntroPagerAdapter(fm: FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> AppIntroDemoFragment()
                1 -> CartDemoFragment()
                2 -> CheckOutDemoFragment()
                else -> AppIntroDemoFragment()
            }
        }

        override fun getCount(): Int {
            return 3
        }
    }
}

