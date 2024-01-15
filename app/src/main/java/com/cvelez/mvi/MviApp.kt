package com.cvelez.mvi

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cvelez.mvi.navigation.Route
import com.cvelez.mvi.presentation.main.MainScreen
import com.cvelez.mvi.presentation.main.MainViewModel
import com.cvelez.mvi.ui.theme.MviSampleTheme
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MviApp() {
    val navController = rememberNavController()
    MviSampleTheme {
        Scaffold(
            content = {
                NavHost(navController = navController, startDestination = Route.MainScreen) {
                    mainScreenRoute(navController = navController)
                }
            }
        )
    }
}

private fun NavGraphBuilder.mainScreenRoute(navController: NavController) {
    composable(Route.MainScreen) {
        val viewModel = koinViewModel<MainViewModel>()
        MainScreen(viewModel = viewModel)
    }
}