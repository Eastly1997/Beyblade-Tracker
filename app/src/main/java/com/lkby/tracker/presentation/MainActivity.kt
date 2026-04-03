package com.lkby.tracker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lkby.common.ui.theme.BeybladeTrackerTheme
import com.lkby.tracker.presentation.navigation.AppNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeybladeTrackerTheme {
                AppNavHost()
            }
        }
    }
}

