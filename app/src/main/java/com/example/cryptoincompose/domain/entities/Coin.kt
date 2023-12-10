package com.example.cryptoincompose.domain.entities

import java.math.BigDecimal

data class Coin(
    val id: String,
    val symbol: String,
    val coinName: String,
    val image: String,
    val currentPrice: BigDecimal,
    val priceChangePercentage24h: Double,
    val lastUpdated: String,
    val priceChangePercentage24hInCurrency: Double
)