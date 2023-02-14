package com.example.apitest.datasource

import com.example.apitest.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OFFDataSource {
    @GET("api/v2/product/{barcode}/?fields=code")
    suspend fun getProductCode(@Path("barcode") barcode:String):ApiResponse
    @GET("api/v2/product/{barcode}/?fields=product_name")
    suspend fun getProductName(@Path("barcode") barcode:String):ApiResponse
    @GET("api/v2/product/{barcode}/?fields=image_url")
    suspend fun getProductPic(@Path("barcode") barcode:String):ApiResponse
}