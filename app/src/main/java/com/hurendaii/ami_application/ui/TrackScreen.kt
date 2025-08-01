package com.hurendaii.ami_application.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TrackScreen(
    friendName: String,
    viewModel: StepCounterViewModel = viewModel(),
    onReturnToCasa: () -> Unit
) {
    val steps by viewModel.steps

    LaunchedEffect(Unit) {
        viewModel.startTracking()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tracking steps for: $friendName", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Steps Walked: $steps", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Distance ≈ ${(steps * 0.8).toInt()} meters") // Rough average step length

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onReturnToCasa) {
            Text("Return to Casa")
        }

        // ❗TEST BUTTON — For emulator use only, delete before production
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { viewModel.incrementStepsManually() }) {
            Text("❗TEST BUTTON: Add Step")
        }
    }
}
