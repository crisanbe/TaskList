package com.cvelez.mvi

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onResume() {
        super.onResume()
        setContent {
            MviApp()
        }
    }
}