package com.example.view.fragment.introfragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.view.activity.MainActivity
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentOrderBinding
import com.example.model.preferences.SharedPreference
import com.example.view.activity.Registration
import com.example.view.fragment.registrationfragments.LoginFragment


class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private lateinit var sharedPreference : SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreference = SharedPreference(requireContext())

        binding.btnLetsShop.setOnClickListener {
            sharedPreference.saveBoolean("isFirstTime", true)

            val intent = Intent(requireContext(),Registration::class.java)
            startActivity(intent)
        }

    }
}