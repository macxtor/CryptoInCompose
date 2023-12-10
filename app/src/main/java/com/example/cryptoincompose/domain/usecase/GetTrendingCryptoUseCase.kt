package com.example.cryptoincompose.domain.usecase

import com.example.cryptoincompose.core.Result
import com.example.cryptoincompose.domain.CryptoRepository
import com.example.cryptoincompose.domain.entities.Coin
import javax.inject.Inject

class GetTrendingCryptoUseCase @Inject constructor(private val repository: CryptoRepository) {

    suspend fun execute(): Result<List<Coin>> = repository.getCoins()
}
