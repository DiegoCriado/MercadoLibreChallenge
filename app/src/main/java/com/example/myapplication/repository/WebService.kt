package com.example.myapplication.repository

import com.example.myapplication.aplication.AppConstants
import com.example.myapplication.model.Product
import com.example.myapplication.model.ProductList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search")
    suspend fun getProductByName(@Query("q") productName: String) : ProductList
}

object RetrofitClient{

    //by lazy permite que la variable webservice se inicie solo cuando se llame a RetrofitClient.webservice
    //Creamos el objeto Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val webService : WebService by lazy {
        retrofit.create(WebService::class.java)
    }

}