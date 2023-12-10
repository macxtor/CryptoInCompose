package com.example.cryptoincompose.data.dto

import java.math.BigDecimal

data class CoinDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: BigDecimal,
    val price_change_percentage_24h: Double,
    val last_updated: String,
    val price_change_percentage_24h_in_currency: Double
)
