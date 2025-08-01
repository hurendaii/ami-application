// Health.kt
package com.hurendaii.ami_application.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HealthScreen(
    friendName: String,
    onTasksClick: () -> Unit,
    onReturnToCasa: () -> Unit,
    onMyAmiClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onMyAmiClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("My AMI")
        }

        Button(
            onClick = onTasksClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tasks")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onReturnToCasa,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Return to Casa")
        }
    }
}


