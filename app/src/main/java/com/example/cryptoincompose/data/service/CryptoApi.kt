package com.example.cryptoincompose.data.service

import com.example.cryptoincompose.data.dto.CoinDto
import retrofit2.http.*

interface CryptoApi {

    @GET("coins/markets")
    suspend fun getCoins(
        @Header("x-cg-demo-api-key") apiKey: String,
        @Query("vs_currency") currency: String,
        @Query("price_change_percentage") priceChangePercentage: String,
        @Query("order") itemOrder: String,
        @Query("per_page") itemPerPage: Int,
        @Query("page") page: Int
    ): List<CoinDto>

}