package com.hurendaii.ami_application.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hurendaii.ami_application.R
import kotlinx.coroutines.delay

@Composable
fun StickmanScreen(
    onStickmanClick: (String) -> Unit
) {
    var showSecondaryPrompt by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(4500)
        showSecondaryPrompt = true
    }

    val stickmen = listOf(
        "Person 1" to R.drawable.stickman_first_pose,
        "Person 2" to R.drawable.stickman_first_pose,
        "Person 3" to R.drawable.stickman_first_pose
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            stickmen.forEach { (name, drawable) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onStickmanClick(name) }
                ) {
                    Image(
                        painter = painterResource(id = drawable),
                        contentDescription = name,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Choose one to be your best friend",
            style = MaterialTheme.typography.titleMedium
        )
        if (showSecondaryPrompt) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Press on your chosen friend",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
