package com.example.cryptoincompose.data.di

import com.example.cryptoincompose.BuildConfig
import com.example.cryptoincompose.data.CryptoRepositoryImpl
import com.example.cryptoincompose.data.service.CryptoApi
import com.example.cryptoincompose.domain.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule @Inject constructor() {

    @Provides
    @Named("baseApiUrl")
    fun provideBaseApiUrl(): String = BuildConfig.BASE_API_URL

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    fun provideProductsRetrofitApi(
        retrofitBuilder: Retrofit.Builder,
        @Named("baseApiUrl") baseUrl: String
    ): Retrofit {
        return retrofitBuilder.baseUrl(baseUrl).build()
    }

    @Provides
    fun provideProductsApiService(retrofit: Retrofit): CryptoApi =
        retrofit.create(CryptoApi::class.java)

    @Provides
    fun provideCryptoRepository(
        cryptoApi: CryptoApi
    ): CryptoRepository = CryptoRepositoryImpl(cryptoApi)
}
