package com.squarecodec.socialapp.android.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.squarecodec.socialapp.android.R
import com.squarecodec.socialapp.android.common.theming.SmallElevation
import com.squarecodec.socialapp.android.destinations.HomeDestination
import com.squarecodec.socialapp.android.destinations.LoginDestination
import com.squarecodec.socialapp.android.destinations.PostDetailDestination
import com.squarecodec.socialapp.android.destinations.SignUpDestination

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val currentDestination = navHostController.currentDestinationAsState().value
    Surface(
        modifier = modifier,
        elevation = SmallElevation
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = appBarTitle(currentDestination?.route))) },
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.surface,
            actions = {
                AnimatedVisibility(visible = currentDestination?.route == HomeDestination.route) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_circle_icon),
                            contentDescription = null
                        )
                    }
                }
            },
            navigationIcon = if (isNavigationIconEnable(currentDestination?.route)) {
                {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            } else {
                null
            }
        )
    }
}

private fun appBarTitle(currentDestination: String?): Int {
    return when (currentDestination) {
        LoginDestination.route -> R.string.login_destination_title
        SignUpDestination.route -> R.string.signup_destination_title
        HomeDestination.route -> R.string.home_destination_title
        PostDetailDestination.route -> R.string.post_detail_destination_title
        else -> R.string.empty_destination
    }
}

private fun isNavigationIconEnable(route: String?): Boolean {
    return !(
            route == LoginDestination.route ||
                    route == SignUpDestination.route ||
                    route == HomeDestination.route
            )
}