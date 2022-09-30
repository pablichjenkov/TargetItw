package com.target.targetcasestudy.data

data class DealItem(
  var id: Int,
  var title: String,
  var description: String,
  var price: String,
  var aisle: String,
  val imageUrl: String = "https://picsum.photos/200/300"
)