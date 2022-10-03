package com.target.targetcasestudy

import com.target.targetcasestudy.api.DealFull
import com.target.targetcasestudy.api.DealPartial
import com.target.targetcasestudy.api.Price

object FakeDataGenerator {

    fun createDealPartial(id: Long): DealPartial {
        return DealPartial(
            id = id,
            title = "Test Title",
            aisle = "1 A",
            description = "Test Product",
            salePrice = Price(100, "$", "$1"),
            imageUrl = "https://www.sample.image.com",
            availability = "instock",
            fulfillment = "online",
        )
    }

    fun createDealFull(id: Long): DealFull {
        return DealFull(
            id = id,
            title = "Test Title",
            aisle = "1 A",
            description = "Test Product",
            salePrice = Price(100, "$", "$1"),
            imageUrl = "https://www.sample.image.com",
            availability = "instock",
            fulfillment = "online",
        )
    }

    fun createPartialDealList(): List<DealPartial> {

        val dealList = mutableListOf<DealPartial>()

        repeat(5) {
            dealList.add(createDealPartial(it.toLong()))
        }

        return dealList
    }

}