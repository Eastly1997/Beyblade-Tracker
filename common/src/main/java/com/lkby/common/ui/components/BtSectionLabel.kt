package com.lkby.common.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun BtSectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        letterSpacing = 1.sp,
        color = Color(0xFF6B7280)
    )
}