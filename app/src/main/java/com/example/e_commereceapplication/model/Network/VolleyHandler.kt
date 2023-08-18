package com.example.e_commereceapplication.model.Network

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_commereceapplication.model.Network.VolleyConstants.Companion.PRODUCT_URL
import com.example.e_commereceapplication.model.Network.VolleyConstants.Companion.SUBCATEGORY__URL
import com.example.e_commereceapplication.model.Network.category.CategoryResponse
import com.example.e_commereceapplication.model.Network.productslist.ProductListResponse
import com.example.e_commereceapplication.model.Network.subCategory.SubcategoryResponse
import com.example.e_commereceapplication.model.Network.User.UserProfile
import com.example.e_commereceapplication.model.Network.VolleyConstants.Companion.PRODUCT_DETAIL_URL
import com.example.e_commereceapplication.model.Network.productDetailsModel.ProductDescriptionResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class VolleyHandler(val context: Context) {

    companion object{
        @SuppressLint("StaticFieldLeak")

        @Volatile
        private var instance: VolleyHandler?= null
        fun getInstance(context:Context) = instance ?:synchronized(this){
            instance ?: VolleyHandler(context)
        }
    }

    private val requestQueue: RequestQueue by lazy{
        Volley.newRequestQueue(context.applicationContext)
    }

    fun registerUser(userProfile: UserProfile, responseCallback: ResponseCallback){

        val jsonRequest = JSONObject()
        jsonRequest.apply {
            put("full_name", userProfile.fullName)
            put("mobile_no", userProfile.phoneNumber)
            put("email_id", userProfile.email)
            put("password", userProfile.password)
        }

        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            VolleyConstants.REGISTER_URL,
            jsonRequest,
            {
                if(it.getString("message").isNotBlank()||it.getString("message").isNotEmpty()){
                    responseCallback.success(null)
                }else{
                    responseCallback.failure()
                }
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)


    }

    fun loginUser(emailId:String, password:String,responseCallback: ResponseCallback){
        val staticEmail = "suchetha@gmail.com"
        val staticPassword = "1234"

        if (emailId == staticEmail && password == staticPassword) {
            responseCallback.success(null)
        } else {
            responseCallback.failure()
        }

//        val jsonRequest = JSONObject()
//        jsonRequest.put("email_id", emailId)
//        jsonRequest.put("password", password)
//
//        val stringRequest = JsonObjectRequest(
//            Request.Method.POST,
//            VolleyConstants.LOGIN_URL,
//            jsonRequest,
//            {
//
//                if(it.getString("message") == "Login successful"){
//                    responseCallback.success(null)
//                }else{
//                    responseCallback.failure()
//                }
//
//
//            },{
//                Log.i("logiError",it.message.toString())
//            }
//        )
//        requestQueue.add(stringRequest)

    }

    fun getCategories(responseCallback: ResponseCallback){

        val stringRequest = StringRequest(
            Request.Method.GET,
            VolleyConstants.CATEGORY_URL,
            {
                val typeToken = object: TypeToken<CategoryResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun getSubCategories(catId:String,responseCallback: ResponseCallback){

        val queryParams = "category_id=$catId"
        val url = "$SUBCATEGORY__URL$queryParams"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<SubcategoryResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                if(response.message == "Success") {
                    responseCallback.success(response)
                } else{
                    responseCallback.failure()
                }
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun getProducts(subCatId:String,responseCallback: ResponseCallback){

        val queryParams = "$subCatId"
        val url = "$PRODUCT_URL$queryParams"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<ProductListResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }

    fun getProductDetails(productId:String,responseCallback: ResponseCallback){

        val url = "$PRODUCT_DETAIL_URL$productId"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object:TypeToken<ProductDescriptionResponse>(){}
                val response = Gson().fromJson(it,typeToken)
                responseCallback.success(response)
            },{
                responseCallback.failure()
            }
        )
        requestQueue.add(stringRequest)
    }


}