package com.example.myapplication.repository

import com.example.myapplication.model.Product
import com.example.myapplication.model.ProductList
import retrofit2.Response

interface Repository {

     suspend fun getProductByName(query: String) : ProductList
}