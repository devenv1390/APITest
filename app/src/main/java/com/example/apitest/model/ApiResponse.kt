package com.example.apitest.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("code")
    val code: String,
    @SerializedName("product")
    val product: ProductX,
    @SerializedName("status")
    val status: Int,
    @SerializedName("status_verbose")
    val statusVerbose: String
)