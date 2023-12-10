package com.example.cryptoincompose.domain

import com.example.cryptoincompose.core.Result
import com.example.cryptoincompose.domain.entities.Coin

interface CryptoRepository {
    suspend fun getCoins(): Result<List<Coin>>
}