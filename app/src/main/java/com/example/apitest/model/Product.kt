package com.example.apitest.model

data class Product(
    val code: String,
    val product_name: String,
    val image_url: String,
    var id: Int = 0
)