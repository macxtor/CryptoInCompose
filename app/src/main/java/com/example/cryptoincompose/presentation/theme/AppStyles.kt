package com.example.cryptoincompose.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class AppStyles {

    private val textStyle = TextStyle()

    val medium14: TextStyle
        @Composable
        get() = textStyle.copy(fontWeight = FontWeight.Medium, fontSize = 14.sp)

    val medium16: TextStyle
        @Composable
        get() = textStyle.copy(fontWeight = FontWeight.Medium, fontSize = 16.sp)

    val semiBold16: TextStyle
        @Composable
        get() = textStyle.copy(fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

}

internal val LocalAppStyles = staticCompositionLocalOf { AppStyles() }
