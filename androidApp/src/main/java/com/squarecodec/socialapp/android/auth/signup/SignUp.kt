package com.squarecodec.socialapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.squarecodec.socialapp.android.destinations.HomeDestination
import com.squarecodec.socialapp.android.destinations.LoginDestination
import com.squarecodec.socialapp.android.destinations.SignUpDestination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SignUp(navigator: DestinationsNavigator) {
    val viewModel: SignUpViewModel = koinViewModel()
    SignUpScreen(
        uiState = viewModel.uiState,
        onUserNameChange = viewModel::updateUserName,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination) {
                popUpTo(SignUpDestination.route) {
                    inclusive = true
                }
            }
        },
        onNavigateToHome = {
            navigator.navigate(HomeDestination) {
                popUpTo(SignUpDestination.route) {
                    inclusive = true
                }
            }
        },
        onSignUpClick = viewModel::signUp
    )
}