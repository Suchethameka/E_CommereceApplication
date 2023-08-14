package com.example.e_commereceapplication.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.e_commereceapplication.data.UserResponse
import com.example.e_commereceapplication.fragments.SignupFragment
import com.example.e_commereceapplication.model.LoginResponseCallback
import com.example.e_commereceapplication.model.VolleyHandler
import com.example.e_commereceapplication.view.LoginActivity
import com.example.e_commereceapplication.view.MainActivity

class LoginPresenter(
    private val view: MVPLogin.LoginView
) : MVPLogin.ILoginPresenter {

    override fun loginUser(email: String, password: String) {
        val staticEmail = "suchetha@gmail.com"
        val staticPassword = "suchetha"

        if (email == staticEmail && password == staticPassword) {
            view.showLoginSuccess()
        } else {
            view.showLoginError("Invalid credentials")
        }
//        VolleyHandler.performLogin(email, password, object : LoginResponseCallback {
//            override fun onLoginSuccess(response: UserResponse) {
//                if (response.status == 1) {
//                    view.showLoginSuccess()
//                } else {
//                    view.showLoginError("Error logging in")
//                }
//            }
//
//            override fun onLoginFailure(error: String) {
//                view.showLoginError("Error logging in")
//            }
//        })
    }

    override fun startMainActivity() {
        val intent = Intent(view.getContext(), MainActivity::class.java)
        view.getContext().startActivity(intent)
        view.finishActivity()
    }

    override fun openSignUpFragment() {
        val transaction: FragmentTransaction = (view as AppCompatActivity).supportFragmentManager.beginTransaction()
        transaction.replace(android.R.id.content, SignupFragment.newInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}






