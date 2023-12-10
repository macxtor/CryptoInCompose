package com.example.cryptoincompose.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue

data class AppColors(
    val themeColors: ColorScheme,
    val coinInfoAppBarIconTint: Color,
    val coinInfoSellBuyBackground: Color,
    val coinItemBackground: Color,
    val coinNameText: Color,
    val priceTextNegative: Color = FireOpal,
    val priceTextPositive: Color = Green,
    val pullRefreshBackground: Color,
    val pullRefreshContent: Color = CaribbeanGreen,
    val text: Color,
    val dialogText: Color,
)

internal val LightColorPalette = AppColors(
    themeColors = lightColorScheme(
        surface = LightGrey,
    ),
    coinInfoAppBarIconTint = Quartz,
    coinInfoSellBuyBackground = White,
    coinItemBackground = White,
    coinNameText = SonicSilver,
    pullRefreshBackground = White,
    text = DarkJungleGreen,
    dialogText = Blue
)


internal val LocalColors = staticCompositionLocalOf { LightColorPalette }
