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
import androidx.navigation.NavController
import com.hurendaii.ami_application.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun FriendChosenScreen(
    name: String,
    navController: NavController
) {
    var showContinuePrompt by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        showContinuePrompt = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = showContinuePrompt) {
                navController.navigate(Screen.Setup.route)
            }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "You chose $name to be your friend.",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            if (showContinuePrompt) {
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
