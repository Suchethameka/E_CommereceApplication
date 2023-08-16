package com.example.e_commereceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.adapters.ViewPagerAdapter
import com.example.e_commereceapplication.databinding.FragmentIntroScreenBinding
import com.example.e_commereceapplication.databinding.IndicatorDotsBinding
import com.example.e_commereceapplication.fragments.AppIntroDemoFragment
import com.example.e_commereceapplication.fragments.CartDemoFragment
import com.example.e_commereceapplication.fragments.CheckOutDemoFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroScreenFragment : Fragment() {

    private lateinit var binding: FragmentIntroScreenBinding
    private lateinit var viewPageAdapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentIntroScreenBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.setDisplayShowHomeEnabled(false)
        initViews()
    }

    private fun initViews(){
        val fragments = listOf<Fragment>(AppIntroDemoFragment(), CartDemoFragment(), CheckOutDemoFragment())
        viewPageAdapter = ViewPagerAdapter(fragments, requireActivity())

        with(binding){
            viewPager2.adapter = viewPageAdapter
            TabLayoutMediator(tabLayout, viewPager2){ tab: TabLayout.Tab, i: Int ->

                tab.customView =createTabView() }.attach()

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    updateTabDots(position)
                }
            })

        }
    }
    private fun createTabView(): View {
        val tabBinding = IndicatorDotsBinding.inflate(layoutInflater)
        return tabBinding.root
    }

    private fun updateTabDots(selectedPosition:Int){

        for(i in 0 until binding.tabLayout.tabCount){
            val tabView = binding.tabLayout.getTabAt(i)?.customView
            val tabBinding = tabView?.let { IndicatorDotsBinding.bind(it) }

            if (i == selectedPosition) {
                tabBinding?.imgDotSelected?.visibility = View.VISIBLE
                tabBinding?.imgDotUnselected?.visibility = View.GONE
            } else {
                tabBinding?.imgDotSelected?.visibility = View.GONE
                tabBinding?.imgDotUnselected?.visibility = View.VISIBLE
            }

        }
    }
}