package com.example.cryptoincompose.data.mapper

import com.example.cryptoincompose.data.dto.CoinDto
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CoinsMapperTest {
    @Test
    fun `toDomain should correctly map properties`() {
        // Given
        val coinDto = CoinDto(
            id = "btc",
            symbol = "BTC",
            name = "Bitcoin",
            image = "bitcoin.png",
            current_price = BigDecimal("50000.0"),
            price_change_percentage_24h = 5.0,
            last_updated = "last_updated",
            price_change_percentage_24h_in_currency = 2.0
        )

        // When
        val coin = coinDto.toDomain()

        // Then
        assertEquals("btc", coin.id)
        assertEquals("BTC", coin.symbol)
        assertEquals("Bitcoin", coin.coinName)
        assertEquals("bitcoin.png", coin.image)
        assertEquals(BigDecimal("50000.0"), coin.currentPrice)
        assertEquals(5.0, coin.priceChangePercentage24h)
        assertEquals("last_updated", coin.lastUpdated)
        assertEquals(2.0, coin.priceChangePercentage24hInCurrency)
    }

}