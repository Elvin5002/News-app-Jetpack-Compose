package com.example.newsapp.data

import com.example.newsapp.data.model.Products
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): Products

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}