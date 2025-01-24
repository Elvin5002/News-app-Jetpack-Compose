package com.example.newsapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.ProductsRepository
import com.example.newsapp.data.Result
import com.example.newsapp.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productsRepository: ProductsRepository
): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            productsRepository.getProducts().collectLatest { result ->
                when (result){
                    is Result.Loading -> {
                        // Show loading
                    }
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                        Log.d("ProductsViewModel", "Error fetching data: ${result.message}")
                    }
                    is Result.Success -> {
                        result.data?.let { products ->
                            _products.update { products }
                        }
                    }
                }

            }
        }
    }
}