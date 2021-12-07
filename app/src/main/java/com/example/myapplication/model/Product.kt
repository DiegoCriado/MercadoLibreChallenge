package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("title")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("thumbnail")
    val image: String
)

data class ProductList(
    @SerializedName("results")
    val results : List<Product>
)
