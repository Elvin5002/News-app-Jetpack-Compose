package com.example.newsapp.data

import com.example.newsapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<Result<List<Product>>>
}