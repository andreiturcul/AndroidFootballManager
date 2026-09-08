package com.example.footballmanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = TurfAccent,
    onPrimary = Color.Black,
    primaryContainer = PitchGreen,
    onPrimaryContainer = Color.White,
    secondary = AccentGold,
    onSecondary = Color.Black,
    background = BackgroundDark,
    surface = CardSurfaceDark,
    onBackground = Color.White,
    onSurface = Color.White,
    error = NegativeRed,
    surfaceVariant = Color(0xFF1F3B25),
    onSurfaceVariant = Color(0xFFE0E0E0)
)

private val LightColors = lightColorScheme(
    primary = PitchGreen,
    onPrimary = Color.White,
    primaryContainer = PitchGreenLight,
    onPrimaryContainer = Color.White,
    secondary = AccentGold,
    onSecondary = Color.Black,
    background = BackgroundLight,
    surface = Color.White,
    onBackground = Color(0xFF0A1A0D),
    onSurface = Color(0xFF0A1A0D),
    error = NegativeRed,
    surfaceVariant = CardSurfaceLight,
    onSurfaceVariant = Color(0xFF1B4D2E)
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
