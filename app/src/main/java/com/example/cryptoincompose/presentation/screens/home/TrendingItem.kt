package com.example.cryptoincompose.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.example.cryptoincompose.domain.entities.Coin
import com.example.cryptoincompose.presentation.components.PriceChange
import com.example.cryptoincompose.presentation.theme.AppTheme
import com.example.cryptoincompose.presentation.theme.spacing_16
import com.example.cryptoincompose.presentation.theme.spacing_40
import com.example.cryptoincompose.presentation.theme.spacing_8
import java.math.BigDecimal

@Suppress("LongMethod")
@Composable
fun TrendingItem(
    modifier: Modifier = Modifier,
    coin: Coin,
    onItemClick: () -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(spacing_8))
            .clickable { onItemClick.invoke() }
            .background(color = AppTheme.colors.coinItemBackground)
            .padding(spacing_8)
    ) {
        val (
            logo,
            coinSymbol,
            coinName,
            priceChange
        ) = createRefs()

        Image(
            modifier = Modifier
                .size(spacing_40)
                .constrainAs(logo) {
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                        topMargin = spacing_8,
                        bottomMargin = spacing_8
                    )
                    start.linkTo(parent.start)
                },
            painter = rememberAsyncImagePainter(coin.image),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .constrainAs(coinSymbol) {
                    top.linkTo(parent.top)
                    bottom.linkTo(coinName.top)
                    start.linkTo(anchor = logo.end, margin = spacing_16)
                },
            text = coin.symbol.uppercase(),
            color = AppTheme.colors.text,
            style = AppTheme.styles.semiBold16
        )

        Text(
            modifier = Modifier
                .constrainAs(coinName) {
                    start.linkTo(coinSymbol.start)
                    top.linkTo(coinSymbol.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.preferredWrapContent
                },
            text = coin.coinName,
            color = AppTheme.colors.coinNameText,
            style = AppTheme.styles.medium14
        )

        PriceChange(
            priceChangePercentage24hInCurrency = coin.priceChangePercentage24hInCurrency,
            modifier = Modifier
                .constrainAs(priceChange) {
                    end.linkTo(parent.end)
                    top.linkTo(coinSymbol.top)
                    bottom.linkTo(coinName.bottom)
                    width = Dimension.preferredWrapContent
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingItemPreview() {
    TrendingItem(coin = Coin(
        id = "id",
        image = "image",
        coinName = "Bitcoin",
        currentPrice = BigDecimal(12450),
        symbol = "BTC",
        lastUpdated = "today",
        priceChangePercentage24h = 12.0,
        priceChangePercentage24hInCurrency = 24.0
    ), onItemClick = {})
}