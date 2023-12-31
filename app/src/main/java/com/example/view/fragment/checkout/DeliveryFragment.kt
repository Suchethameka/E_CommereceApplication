package com.example.view.fragment.checkout

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.view.adapter.AddressAdapter
import com.example.e_commereceapplication.R
import com.example.e_commereceapplication.databinding.AddAddresDialogBinding
import com.example.e_commereceapplication.databinding.FragmentDeliveryBinding
import com.example.model.local.DbHandler
import com.example.model.local.dao.InfoDao
import com.example.model.local.entity.InfoLocal
import com.example.model.preferences.SharedPreference
import com.example.model.remote.dto.Address
import com.example.model.remote.dto.AddressResponse
import com.google.gson.Gson

class DeliveryFragment : Fragment(), AddressAdapter.ItemClickRadioListener {

    private lateinit var binding: FragmentDeliveryBinding
    private lateinit var sharedPreference : SharedPreference
    private lateinit var dbHandler: DbHandler
    private lateinit var infoDao: InfoDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDao()
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreference = SharedPreference(requireContext())
        val userId = sharedPreference.getId("userId")

        getAddresses(userId.toString())

        binding.btnNext.setOnClickListener {
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)
            val currentItem = viewPager.currentItem
            viewPager.setCurrentItem(currentItem + 1, true)
        }

        binding.btnAddAddress.setOnClickListener {
            addAddressDialog()
        }
    }

    private fun getAddresses(userId: String){
        val request = JsonObjectRequest(
            Request.Method.GET, URL_ADDRESS + userId, null,
            { response ->
                val addressResponse = Gson().fromJson(response.toString(), AddressResponse::class.java)

                if (addressResponse.status == 0) {
                    val address = addressResponse.addresses
                    displayAddresses(address)
                } else {
                    val message = addressResponse.message
                    Log.i("tag", message)
                }
            },
            { error ->
                Log.i("tag", error.toString())
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(request)
    }
    private fun displayAddresses(address: List<Address>) {

        val adapter = AddressAdapter(address, this)
        val layoutManager = LinearLayoutManager(requireContext())

    }
    private fun addAddressDialog() {
        val signUpBinding = AddAddresDialogBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireContext()).apply {
            setView(signUpBinding.root)
            setCancelable(true)
        }

        val dialog = builder.create()
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.show()
    }

    override fun onItemClick(address: Address) {
        val newInfo =
            address.title?.let { address.address?.let { it1 -> InfoLocal(infoId = "0", addressTitle = it, address = it1, payment = " " ) } }
        if (newInfo != null) {
            infoDao.addAddress(newInfo)
        }
    }

    private fun initDao() {
        dbHandler = DbHandler(requireContext())
        infoDao = InfoDao(dbHandler)
    }

    companion object {
        const val URL_ADDRESS = "http://10.0.2.2/myshop/index.php/User/addresses/"
    }
}