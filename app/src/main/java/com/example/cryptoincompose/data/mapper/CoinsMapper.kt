package com.example.cryptoincompose.data.mapper

import com.example.cryptoincompose.data.dto.CoinDto
import com.example.cryptoincompose.domain.entities.Coin

fun CoinDto.toDomain() = Coin(
    id = id,
    symbol = symbol,
    coinName = name,
    image = image,
    currentPrice = current_price,
    priceChangePercentage24h = price_change_percentage_24h,
    lastUpdated = last_updated,
    priceChangePercentage24hInCurrency = price_change_percentage_24h_in_currency
)