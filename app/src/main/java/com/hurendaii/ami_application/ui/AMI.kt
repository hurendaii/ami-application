package com.hurendaii.ami_application.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hurendaii.ami_application.R

@Composable
fun AMIScreen(
    friendName: String,
    onBack: () -> Unit
) {
    // Animation states for each element
    val characterAlpha = remember { Animatable(0f) }
    val sleepAlpha = remember { Animatable(0f) }
    val hungerAlpha = remember { Animatable(0f) }
    val boredomAlpha = remember { Animatable(0f) }

    // Sequential animations
    LaunchedEffect(Unit) {
        // Character fades in first (0-500ms)
        characterAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )

        // Sleep stat fades in (500-1000ms)
        sleepAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )

        // Hunger stat fades in (1000-1500ms)
        hungerAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )

        // Boredom stat fades in (1500-2000ms)
        boredomAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Back button top-left (no animation)
        Button(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Text("Return to Health")
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Character image on left, vertically centered
            Image(
                painter = painterResource(id = R.drawable.stickman_first_pose),
                contentDescription = friendName,
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .alpha(characterAlpha.value)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Stats column on right
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                StatRow(
                    name = "Sleep",
                    value = 5,
                    max = 5,
                    alpha = sleepAlpha.value
                )
                Spacer(modifier = Modifier.height(8.dp))
                StatRow(
                    name = "Hunger",
                    value = 5,
                    max = 5,
                    alpha = hungerAlpha.value
                )
                Spacer(modifier = Modifier.height(8.dp))
                StatRow(
                    name = "Boredom",
                    value = 5,
                    max = 5,
                    alpha = boredomAlpha.value
                )
            }
        }
    }
}

@Composable
fun StatRow(name: String, value: Int, max: Int, alpha: Float = 1f) {
    Text(
        text = "$name: $value/$max",
        fontSize = 18.sp,
        modifier = Modifier.alpha(alpha)
    )
}