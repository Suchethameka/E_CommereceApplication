package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.ResponseCallback
import com.example.e_commereceapplication.model.Network.User.UserProfile
import com.example.e_commereceapplication.model.Network.VolleyHandler

class RegisterPresenter(private val volleyHandler: VolleyHandler, val registerView:MVPShoppingCart.RegisterView):
    MVPShoppingCart.IRegisterPresenter {
    override fun registerUser(userProfile: UserProfile) {
        volleyHandler.registerUser(userProfile = userProfile, object: ResponseCallback {
            override fun success(response: Any?) {
                registerView.setSuccess()
            }

            override fun failure() {
                registerView.setError()
            }

        })
    }


}