package com.example.apitest.di

import com.example.apitest.datasource.OFFDataSource
import com.example.apitest.repository.ProductRepository
import com.example.apitest.repository.ProductRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun productRepository(repo: ProductRepositoryImp): ProductRepository

}