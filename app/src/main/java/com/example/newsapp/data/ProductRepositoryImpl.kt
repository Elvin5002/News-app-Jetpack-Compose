package com.example.newsapp.data

import com.example.newsapp.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class ProductRepositoryImpl(
    private val productApi: ProductApi
): ProductsRepository  {
    override suspend fun getProducts(): Flow<Result<List<Product>>> {
        return flow {
            try {
                emit(Result.Loading())
                val products = productApi.getProducts().products
                emit(Result.Success(products))
            } catch (e: IOException){
                e.printStackTrace()
                emit(Result.Error(e.message ?: "An error occurred"))
            } catch (e: HttpException) {
                emit(Result.Error(e.message ?: "An error occurred"))
            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "An error occurred"))
            }
        }
    }
}