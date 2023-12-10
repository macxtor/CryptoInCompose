package com.example.cryptoincompose.domain.usecase

import com.example.cryptoincompose.core.Result
import com.example.cryptoincompose.domain.CryptoRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetTrendingCryptoUseCaseTest {

    @MockK
    val repository: CryptoRepository = mockk()

    private lateinit var getTrendingCryptoUseCase: GetTrendingCryptoUseCase

    @Before
    fun setup() {
        getTrendingCryptoUseCase = GetTrendingCryptoUseCase(repository)
    }


    @Test
    fun `When network request is successful then getCoins returns success`() =
        runTest {
            coEvery { repository.getCoins() } returns Result.Success(listOf())

            val result = getTrendingCryptoUseCase.execute()

            assertThat(result, instanceOf(Result.Success::class.java))
        }

    @Test
    fun `Given valid params when network request fails then getCoins return failure`() =
        runTest {
            val expectedException = IOException("Network error")
            coEvery { repository.getCoins() } returns Result.Error(expectedException)

            val result = getTrendingCryptoUseCase.execute()

            assertThat(result, instanceOf(Result.Error::class.java))
            assertEquals(expectedException, (result as Result.Error).exception)
        }
}