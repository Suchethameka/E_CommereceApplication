package com.example.model.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.model.local.DatabaseConstants.CREATE_TABLE_INFO
import com.example.model.local.DatabaseConstants.CREATE_TABLE_PRODUCTS
import com.example.model.local.DatabaseConstants.DATABASE_NAME
import com.example.model.local.DatabaseConstants.DATABASE_VERSION

class DbHandler(private val context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_PRODUCTS)
        db?.execSQL(CREATE_TABLE_INFO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Upgrades go here
    }
}