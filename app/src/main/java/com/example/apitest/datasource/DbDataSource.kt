package com.example.apitest.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apitest.model.Product
import com.example.apitest.model.ProductDao

@Database(entities = [Product::class], version = 1)
abstract class DbDataSource : RoomDatabase() {

    abstract fun productDao(): ProductDao
}