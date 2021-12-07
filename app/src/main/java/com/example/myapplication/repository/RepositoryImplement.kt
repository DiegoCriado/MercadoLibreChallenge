package com.example.myapplication.repository

import com.example.myapplication.model.DataSource
import com.example.myapplication.model.ProductList

class RepositoryImplement(val dataSource: DataSource):  Repository {

    override suspend fun getProductByName(query: String): ProductList = dataSource.getProductByName(query)
}