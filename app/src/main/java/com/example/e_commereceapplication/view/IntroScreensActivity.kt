package com.example.e_commereceapplication.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commereceapplication.databinding.IndicatorDotsBinding
import com.google.android.material.tabs.TabLayoutMediator
import android.view.View
import com.example.e_commereceapplication.adapters.ViewPagerAdapter
import com.example.e_commereceapplication.databinding.ActivityIntroScreensBinding
import com.example.e_commereceapplication.fragments.AppIntroDemoFragment
import com.example.e_commereceapplication.fragments.CartDemoFragment
import com.example.e_commereceapplication.fragments.CheckOutDemoFragment

class IntroScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroScreensBinding
    private lateinit var viewpagerAdapter: ViewPagerAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("intro", Context.MODE_PRIVATE)

        if (isFirstTimeUser()) {
            initViews()
        } else {
            startLoginActivity()
        }
    }

    private fun isFirstTimeUser(): Boolean {
        return sharedPreferences.getBoolean("isFirstTime", true)
    }

    private fun setIntroScreenSeen() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isFirstTime", false)
        editor.apply()
    }

    private fun initViews() {
        val fragments = listOf(AppIntroDemoFragment(), CartDemoFragment(), CheckOutDemoFragment())
        viewpagerAdapter = ViewPagerAdapter(
            fragments,
            this@IntroScreenActivity
        )
        with(binding) {
            viewPager.adapter = viewpagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, _ ->
                tab.customView = createTabView()
            }.attach()

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    updateTabDots(position)
                }
            })

            skipButton.setOnClickListener {
                setIntroScreenSeen()
                startLoginActivity()
            }
        }
    }

    private fun startLoginActivity() {
        startActivity(Intent(this@IntroScreenActivity, LoginActivity::class.java))
        finish()
    }

    private fun createTabView(): View {
        val tabBinding = IndicatorDotsBinding.inflate(layoutInflater)
        return tabBinding.root
    }

    private fun updateTabDots(selectedPosition: Int) {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tabView = binding.tabLayout.getTabAt(i)?.customView
            val tabBinding = IndicatorDotsBinding.bind(tabView!!)

            if (i == selectedPosition) {
                tabBinding.imgDotSelected.visibility = View.VISIBLE
                tabBinding.imgDotUnselected.visibility = View.GONE
            } else {
                tabBinding.imgDotSelected.visibility = View.GONE
                tabBinding.imgDotUnselected.visibility = View.VISIBLE
            }
        }
    }
}





