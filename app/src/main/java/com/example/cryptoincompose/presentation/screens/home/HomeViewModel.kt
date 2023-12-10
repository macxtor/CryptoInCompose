package com.example.cryptoincompose.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoincompose.core.Result
import com.example.cryptoincompose.domain.entities.Coin
import com.example.cryptoincompose.domain.usecase.GetTrendingCryptoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingCryptoUseCase: GetTrendingCryptoUseCase
) : ViewModel() {

    private val _trendingCryptoState: MutableStateFlow<TrendingCryptoState> =
        MutableStateFlow(TrendingCryptoState.Loading)

    init {
        fetchTrendingCrypto()
    }

    val trendingCryptoState = _trendingCryptoState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = TrendingCryptoState.Loading
    )

    private fun fetchTrendingCrypto() {
        viewModelScope.launch {
            setTrendingCryptoState(TrendingCryptoState.Loading)
            val response = getTrendingCryptoUseCase.execute()
            handleGetTrendingCryptoResponse(response)
        }
    }

    private fun handleGetTrendingCryptoResponse(response: Result<List<Coin>>) {
        when (response) {
            is Result.Success -> setTrendingCryptoState(TrendingCryptoState.Success(response.data))
            is Result.Error -> setTrendingCryptoState(TrendingCryptoState.Error(response.exception))
        }
    }

    private fun setTrendingCryptoState(state: TrendingCryptoState) {
        _trendingCryptoState.update {
            state
        }
    }
}