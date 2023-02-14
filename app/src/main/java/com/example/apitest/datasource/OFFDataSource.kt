package com.example.apitest.datasource

import com.example.apitest.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OFFDataSource {
    @GET("api/v2/product/{barcode}/?fields=code,product_name,image_url,brands")
    suspend fun getProduct(@Path("barcode") barcode:String):ApiResponse
//    @GET("api/v2/product/{barcode}/?fields=code,product_name,image_url,brands")
//    suspend fun getProductCode(@Path("barcode") barcode:String):ApiResponse
//    @GET("api/v2/product/{barcode}/?fields=")
//    suspend fun getProductName(@Path("barcode") barcode:String,@Query("username") username:String,@Query("password")password:String):ApiResponse
//    @GET("api/v2/product/{barcode}/?fields=")
//    suspend fun getProductPic(@Path("barcode") barcode:String,@Query("username") username:String,@Query("password")password:String):ApiResponse
//    @GET("api/v2/product/{barcode}/?fields=")
//    suspend fun getProductBrands(@Path("barcode") barcode:String,@Query("username") username:String,@Query("password")password:String):ApiResponse
}