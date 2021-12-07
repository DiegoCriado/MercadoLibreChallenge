package com.example.myapplication.model

import com.example.myapplication.repository.WebService

class DataSource(val webService: WebService) {

    suspend fun getProductByName(query: String) : ProductList =  webService.getProductByName(query)
}