package com.lkby.tracker.presentation.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun loginBackground(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0D1B2A),
            Color(0xFF1B263B),
            Color(0xFF415A77)
        )
    )
}