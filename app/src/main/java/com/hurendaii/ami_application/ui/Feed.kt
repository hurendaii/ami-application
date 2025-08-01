package com.hurendaii.ami_application.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.*
import kotlin.math.min

@Composable
fun FeedScreen(
    friendName: String,
    onBack: () -> Unit
) {
    var swipeProgress by remember { mutableStateOf(0f) } // 0.0 to 1.0
    var unlocked by remember { mutableStateOf(false) }
    var success by remember { mutableStateOf(false) }

    val threshold = 0.9f // percent of the bar to unlock

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unzip the package to feed $friendName")
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.LightGray)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (!unlocked) {
                            swipeProgress = (swipeProgress + dragAmount / 600f).coerceIn(0f, 1f)
                            if (swipeProgress >= threshold) {
                                unlocked = true
                            }
                        }
                    }
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(
                    color = Color.Green,
                    size = size.copy(width = size.width * swipeProgress)
                )
            }
            Text(
                text = if (unlocked) "Unlocked!" else "Swipe to Feed",
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { success = true },
            enabled = unlocked
        ) {
            Text("Continue")
        }

        if (success) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("✅ Success! You fed $friendName")
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack) {
            Text("Back to Casa")
        }
    }
}
