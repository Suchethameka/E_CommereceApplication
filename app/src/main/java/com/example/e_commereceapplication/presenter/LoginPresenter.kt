package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.ResponseCallback
import com.example.e_commereceapplication.model.Network.VolleyHandler


class LoginPresenter(private val volleyHandler: VolleyHandler, val loginView: MVPShoppingCart.LoginView): MVPShoppingCart.ILoginPresenter {
    override fun validateUser(emailId: String, password: String) {
        volleyHandler.loginUser(emailId, password, object: ResponseCallback {
            override fun success(response: Any?) {
                loginView.setSuccess()
            }
            override fun failure() {
                loginView.setError()
            }

        })
    }

    /*
    val image = catergory.category_image_url -> smartphones.png
    val url = "http://localhost/myshop/images/$image" ->
    picasso.get().load(url).into(view)
    * */
}