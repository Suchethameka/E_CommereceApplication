package com.example.e_commereceapplication.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.e_commereceapplication.data.Category
import com.example.e_commereceapplication.data.CategoryResponse
import com.example.e_commereceapplication.data.RegisterResponse
import com.example.e_commereceapplication.data.UserRegistrationData
import com.example.e_commereceapplication.data.UserResponse
import com.example.e_commereceapplication.data.UserX
import org.json.JSONException
import org.json.JSONObject

class VolleyHandler(private val context: Context) {
    private val requestQueue: RequestQueue =
        Volley.newRequestQueue(context.applicationContext)



    fun performSignup(userData: UserRegistrationData, callback: SignupResponseCallback) {

            val url = BASE_URL_REGISTER
            val jsonObject = JSONObject().apply {
                put("email_id", userData.email)
                put("password", userData.password)
                put("full_name", userData.fullName)
                put("mobile_no", userData.phoneNumber)
            }


        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val message = response.getString("message")
                val status = response.getInt("status")


                if (status == 0) {
                    callback.onSignupSuccess(RegisterResponse(message, status))
                } else {
                    callback.onSignupFailure("Error registering user during request")
                }
            },
            { error ->
                callback.onSignupFailure("Error registering user")
            }
        )


        requestQueue.add(request)
    }



    fun performLogin(email: String, password: String, callback: LoginResponseCallback) {
            val url = BASE_URL_LOGIN

            val jsonObject = JSONObject().apply {
                put("email_id", email)
                put("password", password)
            }

            val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                { response ->
                    val message = response.getString("message")
                    val status = response.getInt("status")
                    val userJson = response.getJSONObject("user")

                    if (status == 0) {
                        val user = UserX(
                            userJson.getString("email_id"),
                            userJson.getString("full_name"),
                            userJson.getString("mobile_no"),
                            userJson.getString("password")
                        )
                        callback.onLoginSuccess(UserResponse(message, status, user))
                    } else {
                        callback.onLoginFailure("Error logging in")
                    }
                },
                { error ->
                    callback.onLoginFailure("Error logging in")
                }
            )

            requestQueue.add(request)
        }

    fun fetchCategories(callback: CategoryResponseCallback) {
            val url = BASE_URL_CATEGORY

            val request = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val message = response.getString("message")
                    val status = response.getInt("status")
                    val categoriesJsonArray = response.getJSONArray("categories")

                    val categories = mutableListOf<Category>()
                    for (i in 0 until categoriesJsonArray.length()) {
                        val categoryJson = categoriesJsonArray.getJSONObject(i)
                        val category = Category(
                            categoryJson.getString("category_id"),
                            categoryJson.getString("category_image_url"),
                            categoryJson.getString("category_name"),
                            categoryJson.getString("is_active")
                        )
                        categories.add(category)
                    }

                    callback.onCategoryFetchSuccess(CategoryResponse(categories, message, status))
                },
                { error ->
                    callback.onCategoryFetchFailure("Error fetching categories")
                }
            )

            requestQueue.add(request)
        }
    companion object {
        const val BASE_URL_REGISTER = "http://10.0.2.2/myshop/index.php/User/register"
        const val BASE_URL_REGISTER_POSTMAN = "http://localhost/myshop/index.php/User/register"
        const val BASE_URL_LOGIN = "http://10.0.2.2/myshop/index.php/User/auth"
        const val BASE_URL_CATEGORY = "http://10.0.2.2/myshop/index.php/Category"
    }

}




