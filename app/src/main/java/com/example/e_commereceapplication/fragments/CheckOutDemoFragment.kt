package com.example.e_commereceapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.FragmentCheckOutDemoBinding
import com.example.e_commereceapplication.view.LoginFragment


class CheckOutDemoFragment : Fragment() {
    private lateinit var binding:FragmentCheckOutDemoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckOutDemoBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,
                LoginFragment()
            )?.commit()
        }
    }
}