package com.squarecodec.socialapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.squarecodec.socialapp.android.common.components.AppBar
import com.squarecodec.socialapp.android.destinations.HomeDestination
import com.squarecodec.socialapp.android.destinations.LoginDestination

@Composable
fun SocialApp(token: String?) {
    val navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()
    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colors.surface
    } else {
        MaterialTheme.colors.surface.copy(alpha = 0.95f)
    }

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppBar(navHostController = navHostController) }
    ) { innerPaddings ->
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(innerPaddings),
            navController = navHostController
        )
    }

    LaunchedEffect(key1 = token, block = {
        if (token != null && token.isEmpty()) {
            navHostController.navigate(LoginDestination.route) {
                popUpTo(HomeDestination.route) {
                    inclusive = true
                }
            }
        }
    })
}