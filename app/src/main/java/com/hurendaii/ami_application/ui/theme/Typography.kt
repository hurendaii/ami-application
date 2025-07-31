// Typography.kt
package com.hurendaii.ami_application.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.hurendaii.ami_application.R

val Consolas = FontFamily(
    Font(R.font.couri)
)

val AppTypography = Typography(
    displayLarge = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    displayMedium = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    displaySmall = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    headlineLarge = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    headlineMedium = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    headlineSmall = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    titleLarge = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    titleMedium = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    titleSmall = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    bodyLarge = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    bodyMedium = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    bodySmall = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    labelLarge = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    labelMedium = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
    labelSmall = androidx.compose.ui.text.TextStyle(fontFamily = Consolas),
)
