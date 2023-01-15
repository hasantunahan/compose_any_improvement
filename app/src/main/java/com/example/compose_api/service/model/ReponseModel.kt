package com.example.compose_api.service.model

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(
    val page : Int,
    @SerializedName("per_page")
    val perPage : Int,
    val total : Int,
    @SerializedName("total_pages")
    val totalPages : Int,
    val data : T,
)