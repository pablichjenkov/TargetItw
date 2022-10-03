package com.target.targetcasestudy.api

import com.google.gson.annotations.SerializedName

data class DealPartial(
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("aisle")
    val aisle: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("regular_price")
    val salePrice: Price,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("availability")
    val availability: String,

    @SerializedName("fulfillment")
    val fulfillment: String,
)