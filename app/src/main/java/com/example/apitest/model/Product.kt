package com.example.apitest.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "product")
data class Product(
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "product_name") val product_name: String,
    @ColumnInfo(name = "image") val image_url: String,
    @ColumnInfo(name = "brands") val brands: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Query("SELECT * FROM product ORDER BY id DESC")
    fun getAll(): LiveData<List<Product>>

    @Delete
    fun delete(product: Product)
}