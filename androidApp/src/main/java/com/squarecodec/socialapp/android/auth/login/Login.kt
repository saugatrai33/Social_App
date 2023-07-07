package com.squarecodec.socialapp.android.auth.login

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.squarecodec.socialapp.android.destinations.HomeDestination
import com.squarecodec.socialapp.android.destinations.LoginDestination
import com.squarecodec.socialapp.android.destinations.SignUpDestination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Login(navigator: DestinationsNavigator) {
    val viewModel: LoginViewModel = koinViewModel()
    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateToHome = {
            navigator.navigate(HomeDestination) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }
        },
        onSignInClick = viewModel::signIn,
        onNavigateSignUp = {
            navigator.navigate(SignUpDestination) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }
        }
    )
}