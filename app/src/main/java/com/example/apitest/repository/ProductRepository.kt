package com.example.apitest.repository

import com.example.apitest.datasource.OFFDataSource
import com.example.apitest.model.Product
import javax.inject.Inject

interface ProductRepository {
    suspend fun getNewProduct(barcode: String): Product
}

class ProductRepositoryImp @Inject constructor(
    private val dataSource: OFFDataSource,
) : ProductRepository {

    override suspend fun getNewProduct(barcode: String): Product {
        val code = dataSource.getProductCode(barcode).code
        val name = dataSource.getProductCode(barcode).product.productName
        val picture = dataSource.getProductCode(barcode).product.imageUrl
        val product = Product(code, name, picture)
        return product
    }
}