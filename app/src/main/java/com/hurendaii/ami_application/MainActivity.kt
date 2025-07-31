package com.hurendaii.ami_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hurendaii.ami_application.navigation.AppNavGraph
import com.hurendaii.ami_application.ui.theme.Ami_applicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ami_applicationTheme {
                AppNavGraph()  // Navigation graph to handle all screens
            }
        }
    }
}
