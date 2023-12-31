package com.example.model.local.dao

import android.content.ContentValues
import com.example.model.local.DatabaseConstants
import com.example.model.local.DatabaseConstants.TABLE_INFO
import com.example.model.local.DbHandler
import com.example.model.local.entity.InfoLocal

class InfoDao(private val dbHandler: DbHandler) {


    fun addAddress(infoLocal: InfoLocal): Long {
        val contentValue = ContentValues()

        contentValue.apply {
            put("addressTitle", infoLocal.addressTitle)
            put("address", infoLocal.address)
            put("payment", infoLocal.payment)
        }

        return dbHandler.writableDatabase.insert(TABLE_INFO, null, contentValue)
    }


    fun updateSummary(infoId: String, newPayment: String) : Int {
        val contentValue = ContentValues()

        contentValue.apply {
            put("payment", newPayment )
        }

        val selection = "product_id = ?"
        val selectionArgs = arrayOf(infoId)

        return dbHandler.writableDatabase.update(DatabaseConstants.TABLE_PRODUCTS, contentValue, selection, selectionArgs)
    }

}