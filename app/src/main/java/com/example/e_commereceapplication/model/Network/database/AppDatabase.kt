package com.example.e_commereceapplication.model.Network.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_commereceapplication.model.Network.cart.CartItem
import com.example.e_commereceapplication.model.Network.database.DatabaseConstants.DATABASE_NAME

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun getCartDao():CartDao
    companion object{
        private var INSTANCE:AppDatabase?=null

        fun getAppDatabase(context: Context):AppDatabase{

            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME,
                ).allowMainThreadQueries().build()
            }
            return INSTANCE as AppDatabase

        }
    }
}