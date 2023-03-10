package com.example.apitest.model


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("code")
    val code: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("brands")
    val brands: String,
)