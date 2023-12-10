package com.example.cryptoincompose.data

import com.example.cryptoincompose.core.Result
import com.example.cryptoincompose.data.mapper.toDomain
import com.example.cryptoincompose.data.service.CryptoApi
import com.example.cryptoincompose.domain.CryptoRepository
import com.example.cryptoincompose.domain.entities.Coin
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoApi: CryptoApi
) : CryptoRepository {

    companion object {
        private const val INITIAL_PAGE = 1
        private const val ITEM_PER_PAGE = 20
        private const val VS_CURRENCY = "gbp"
        private const val COINS_ORDER = "market_cap_desc"
        private const val MY_COINS_PRICE_CHANGE_IN_HOUR = "24h"
    }
    override suspend fun getCoins(): Result<List<Coin>> {
        return try {
            Result.Success(
                cryptoApi.getCoins(
                    itemPerPage = ITEM_PER_PAGE,
                    itemOrder = COINS_ORDER,
                    currency = VS_CURRENCY,
                    page = INITIAL_PAGE,
                    priceChangePercentage = MY_COINS_PRICE_CHANGE_IN_HOUR
                ).map { it.toDomain() })
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}