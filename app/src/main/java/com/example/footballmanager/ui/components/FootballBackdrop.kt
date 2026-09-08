@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.footballmanager.ui.theme.PitchGreen

enum class FootballScene(val imageUrl: String) {
    STADIUM("https://images.unsplash.com/photo-1522778119026-d647f0596c20?auto=format&fit=crop&w=1400&q=70"),
    PLAYERS("https://images.unsplash.com/photo-1579952363873-27f3bade9f55?auto=format&fit=crop&w=1400&q=70"),
    TRANSFERS("https://images.unsplash.com/photo-1508098682722-e99c43a406b2?auto=format&fit=crop&w=1400&q=70"),
    PITCH("https://images.unsplash.com/photo-1556056504-5c7696c4c28d?auto=format&fit=crop&w=1400&q=70"),
    GOAT("https://images.unsplash.com/photo-1574629810360-7efbbe195018?auto=format&fit=crop&w=1400&q=70"),
    CROWD("https://images.unsplash.com/photo-1489944440615-453fc2c6a4f2?auto=format&fit=crop&w=1400&q=70"),
    MATCH("https://images.unsplash.com/photo-1431324155629-1a6deb1dec8d?auto=format&fit=crop&w=1400&q=70")
}

@Composable
fun footballTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    cursorColor = Color.White,
    focusedBorderColor = Color(0xFF86C55E),
    unfocusedBorderColor = Color(0xFF86C55E),
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color(0xFFD1FAE5),
    focusedPlaceholderColor = Color(0xFFD1FAE5),
    unfocusedPlaceholderColor = Color(0xFFD1FAE5),
    focusedLeadingIconColor = Color.White,
    unfocusedLeadingIconColor = Color(0xFFD1FAE5),
    focusedContainerColor = Color.Black.copy(alpha = 0.25f),
    unfocusedContainerColor = Color.Black.copy(alpha = 0.25f)
)

@Composable
fun footballTopBarColors(): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
    containerColor = Color(0xCC14532D),
    titleContentColor = Color.White,
    actionIconContentColor = Color.White,
    navigationIconContentColor = Color.White
)

@Composable
fun FootballPageBackground(scene: FootballScene, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        PitchGrassCanvas(Modifier.fillMaxSize())
        AsyncImage(
            model = scene.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.55f
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xAA0B3D1E),
                            Color(0x99082A14),
                            Color(0xBB0B3D1E)
                        )
                    )
                )
        )
    }
}

@Composable
fun FootballPage(
    scene: FootballScene,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier.fillMaxSize()) {
        FootballPageBackground(scene)
        content()
    }
}

@Composable
private fun PitchGrassCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier) {
        val stripeCount = 10
        val stripeWidth = size.width / stripeCount
        for (i in 0 until stripeCount) {
            drawRect(
                color = if (i % 2 == 0) Color(0xFF1B7A3C) else Color(0xFF166534),
                topLeft = Offset(i * stripeWidth, 0f),
                size = Size(stripeWidth, size.height)
            )
        }
        val white = Color.White.copy(alpha = 0.18f)
        val pad = 28f
        drawRect(
            color = white,
            topLeft = Offset(pad, pad),
            size = Size(size.width - pad * 2, size.height - pad * 2),
            style = Stroke(width = 4f)
        )
        drawLine(
            color = white,
            start = Offset(size.width / 2, pad),
            end = Offset(size.width / 2, size.height - pad),
            strokeWidth = 4f
        )
        drawCircle(
            color = white,
            radius = size.minDimension / 8,
            center = Offset(size.width / 2, size.height / 2),
            style = Stroke(width = 4f)
        )
        drawCircle(
            color = PitchGreen.copy(alpha = 0.0f),
            radius = 8f,
            center = Offset(size.width / 2, size.height / 2)
        )
        drawCircle(color = white, radius = 8f, center = Offset(size.width / 2, size.height / 2))
    }
}
