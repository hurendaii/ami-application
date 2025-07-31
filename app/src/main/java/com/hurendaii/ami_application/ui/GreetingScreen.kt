package com.hurendaii.ami_application.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun GreetingScreen(
    username: String,
    onContinue: () -> Unit
) {
    var showTapToContinue by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        showTapToContinue = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = showTapToContinue) {
                onContinue()
            }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hi nice to meet you $username!",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            if (showTapToContinue) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tap anywhere to continue",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
