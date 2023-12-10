package com.example.cryptoincompose.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoincompose.R
import com.example.cryptoincompose.domain.entities.Coin
import com.example.cryptoincompose.presentation.theme.AppTheme
import com.example.cryptoincompose.presentation.theme.spacing_16

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.trendingCryptoState.collectAsState()
    Scaffold(
        content = { paddingValues ->
            ProductListContent(
                state = state.value,
                paddingValues = paddingValues
            )
        })
}

@Composable
fun ProductListContent(paddingValues: PaddingValues, state: TrendingCryptoState) {
    when (state) {
        is TrendingCryptoState.Success -> ProductListSuccess(
            listOfTrendingCoins = state.listOfCrypto,
            paddingValues = paddingValues
        )

        is TrendingCryptoState.Loading -> LoadingView()
        is TrendingCryptoState.Error -> ProductListError(exception = state.exception.message)
    }
}

@Composable
fun ProductListSuccess(listOfTrendingCoins: List<Coin>, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = paddingValues
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing_16)
            ) {
                Text(
                    text = stringResource(id = R.string.home_trending_title),
                    style = AppTheme.styles.medium16,
                    color = AppTheme.colors.text
                )
            }
        }

        itemsIndexed(listOfTrendingCoins) { _, coin ->
            TrendingItem(
                modifier = Modifier.padding(horizontal = spacing_16),
                coin = coin,
                onItemClick = {}
            )

        }
    }
}

@Composable
fun ProductListError(exception: String?) {
    exception?.let {
        Column(
            modifier = Modifier
                .padding(spacing_16)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = it)
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .padding(spacing_16)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}