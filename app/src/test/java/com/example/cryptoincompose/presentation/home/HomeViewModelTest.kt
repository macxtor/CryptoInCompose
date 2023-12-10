package com.example.cryptoincompose.presentation.home

import com.example.cryptoincompose.core.Result
import com.example.cryptoincompose.domain.entities.Coin
import com.example.cryptoincompose.domain.usecase.GetTrendingCryptoUseCase
import com.example.cryptoincompose.presentation.screens.home.HomeViewModel
import com.example.cryptoincompose.presentation.screens.home.TrendingCryptoState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @MockK
    private lateinit var getTrendingCryptoUseCase: GetTrendingCryptoUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(getTrendingCryptoUseCase)
    }

    @Test
    fun `fetchTrendingCrypto sets Loading state and updates with Success when successful`(): Unit =
        runTest {
            // Given
            val mockCoins = listOf<Coin>(/* your mock data here */)
            coEvery { getTrendingCryptoUseCase.execute() } returns Result.Success(mockCoins)

            // When
            val result = homeViewModel.trendingCryptoState

            // Then
            assertEquals(TrendingCryptoState.Loading, homeViewModel.trendingCryptoState.value)
            advanceUntilIdle()
            assertEquals(
                TrendingCryptoState.Success(mockCoins),
                result.value
            )
        }

    @Test
    fun `fetchTrendingCrypto sets Loading state and updates with Error when unsuccessful`() =
        runTest {
            // Given
            val expectedException = IOException("Network error")
            coEvery { getTrendingCryptoUseCase.execute() } returns Result.Error(expectedException)

            // When
            val result = homeViewModel.trendingCryptoState

            // Then
            assertEquals(TrendingCryptoState.Loading, result.value)
            advanceUntilIdle()
            assertEquals(
                TrendingCryptoState.Error(expectedException),
                result.value
            )
        }
}