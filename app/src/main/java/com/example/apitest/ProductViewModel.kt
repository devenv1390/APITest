package com.example.apitest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.model.Product
import com.example.apitest.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepo: ProductRepository
):ViewModel() {

    private val _isLoading:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val products:LiveData<List<Product>> by lazy {
        productRepo.getAllProduct()
    }

    val isLoading:LiveData<Boolean> get() = _isLoading

    fun addProduct(barcode:String){
        if (_isLoading.value==false)
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                productRepo.getNewProduct(barcode)
                _isLoading.postValue(false)
            }
    }

    fun deleteProduct(toDelete: Product){
        viewModelScope.launch(Dispatchers.IO) {
            productRepo.deleteProduct(toDelete)
        }
    }
}