package com.target.targetcasestudy.api

import com.google.gson.annotations.SerializedName

class DealsResponse {
    @SerializedName("products")
    var products: List<Deal> = emptyList()
}