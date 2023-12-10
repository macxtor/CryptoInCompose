package com.example.cryptoincompose.data

import com.example.cryptoincompose.core.Result
import com.example.cryptoincompose.data.dto.CoinDto
import com.example.cryptoincompose.data.mapper.toDomain
import com.example.cryptoincompose.data.service.CryptoApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CryptoRepositoryImplTest {

    @MockK
    private lateinit var cryptoApi: CryptoApi

    private lateinit var cryptoRepository: CryptoRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cryptoRepository = CryptoRepositoryImpl(cryptoApi)
    }

    @Test
    fun `getCoins should return a list of coins on successful API call`() = runTest {
        // Given
        val mockCoinsApiResponse = listOf<CoinDto>()
        val expectedResult = Result.Success(mockCoinsApiResponse.map { it.toDomain() })
        coEvery {
            cryptoApi.getCoins(
                itemOrder = any(),
                itemPerPage = any(),
                priceChangePercentage = any(),
                currency = any(),
                page = any()
            )
        } returns (mockCoinsApiResponse)

        // When
        val result = cryptoRepository.getCoins()

        // Then
        assertEquals(expectedResult, result)
        assertThat(
            result,
            instanceOf(Result.Success::class.java)
        )
    }


    @Test
    fun `getCoins should return an error result on API failure`() = runTest {
        // Given
        val expectedException = IOException("Network error")
        coEvery {
            cryptoApi.getCoins(
                itemOrder = any(),
                itemPerPage = any(),
                priceChangePercentage = any(),
                currency = any(),
                page = any()
            )
        } throws expectedException


        // When
        val result = cryptoRepository.getCoins()

        // Then
        assertThat(
            result,
            instanceOf(Result.Error::class.java)
        )
        assertTrue(result is Result.Error)
        assertEquals(expectedException, (result as Result.Error).exception)
    }
}