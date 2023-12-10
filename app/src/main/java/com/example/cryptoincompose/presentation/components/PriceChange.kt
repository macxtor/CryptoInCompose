package com.example.cryptoincompose.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptoincompose.R
import com.example.cryptoincompose.presentation.extension.toFormattedString
import com.example.cryptoincompose.presentation.theme.AppTheme
import com.example.cryptoincompose.presentation.theme.spacing_12
import com.example.cryptoincompose.presentation.theme.spacing_8
import kotlin.math.abs

@Composable
fun PriceChange(
    priceChangePercentage24hInCurrency: Double,
    modifier: Modifier,
    displayForDetailPage: Boolean = false
) {
    val isPositiveNumber = priceChangePercentage24hInCurrency >= 0

    Row(modifier = modifier) {
        Icon(
            modifier = Modifier
                .padding(end = if (displayForDetailPage) spacing_8 else spacing_12)
                .align(alignment = Alignment.CenterVertically),
            painter = if (isPositiveNumber) {
                painterResource(id = R.drawable.ic_arrow_up)
            } else {
                painterResource(id = R.drawable.ic_arrow_down)
            },
            tint = if (isPositiveNumber) AppTheme.colors.priceTextPositive else AppTheme.colors.priceTextNegative,
            contentDescription = null
        )

        Text(
            text = stringResource(
                R.string.coin_profit_percent,
                abs(priceChangePercentage24hInCurrency).toFormattedString()
            ),
            style = if (displayForDetailPage) AppTheme.styles.medium14 else AppTheme.styles.medium16,
            color = if (isPositiveNumber) AppTheme.colors.priceTextPositive else AppTheme.colors.priceTextNegative
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PriceChangePreview() {
    PriceChange(priceChangePercentage24hInCurrency = 24.0, modifier = Modifier)
}