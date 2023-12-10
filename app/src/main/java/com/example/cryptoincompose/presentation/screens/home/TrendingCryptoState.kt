package com.example.cryptoincompose.presentation.screens.home

import com.example.cryptoincompose.domain.entities.Coin

sealed class TrendingCryptoState {
    data object Loading : TrendingCryptoState()
    data class Success(val listOfCrypto: List<Coin>) : TrendingCryptoState()
    data class Error(val exception: Exception) : TrendingCryptoState()
}