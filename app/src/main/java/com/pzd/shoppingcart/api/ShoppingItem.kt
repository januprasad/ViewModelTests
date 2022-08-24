package com.pzd.shoppingcart.api

data class ShoppingItem(
    var name: String,
    var amount: Int,
    var price: Float,
    var imageUrl: String,
    val id: Int? = null
)
