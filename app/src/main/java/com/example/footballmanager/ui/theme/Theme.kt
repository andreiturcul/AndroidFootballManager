package com.example.footballmanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = PrimaryBlueLight,
    secondary = AccentGold,
    background = BackgroundDark,
    surface = SurfaceDark,
    error = NegativeRed
)

private val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    secondary = AccentGold,
    background = Color(0xFFF5F6FA),
    surface = Color(0xFFFFFFFF)
)

@Composable
fun FootballManagerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}
