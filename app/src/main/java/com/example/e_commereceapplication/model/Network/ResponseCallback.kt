package com.example.e_commereceapplication.model.Network

interface ResponseCallback {

    fun success(response:Any?)
    fun failure()

}