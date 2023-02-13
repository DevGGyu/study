package com.example.roomdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ProductRepository constructor(private val productDao: ProductDao) {

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    val searchResult = MutableLiveData<List<Product>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertProduct(newProduct: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.insertProduct(product = newProduct)
        }
    }

    fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.deleteProduct(name)
        }
    }

    fun findProduct(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResult.value = asyncFind(name)
        }
    }

    private suspend fun asyncFind(name: String): List<Product>? {
        return coroutineScope.async(Dispatchers.IO) {
            return@async productDao.findProduct(name)
        }.await()
    }
}