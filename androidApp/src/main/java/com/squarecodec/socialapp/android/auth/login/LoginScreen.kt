package com.squarecodec.socialapp.android.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.squarecodec.socialapp.android.R
import com.squarecodec.socialapp.android.common.components.CustomBtn
import com.squarecodec.socialapp.android.common.components.CustomTextField
import com.squarecodec.socialapp.android.common.theming.ButtonHeight
import com.squarecodec.socialapp.android.common.theming.DefaultElevation
import com.squarecodec.socialapp.android.common.theming.ExtraLargeSpacing
import com.squarecodec.socialapp.android.common.theming.LargeSpacing
import com.squarecodec.socialapp.android.common.theming.MediumSpacing
import com.squarecodec.socialapp.android.common.theming.SmallSpacing

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onSignInClick: () -> Unit,
    onNavigateSignUp: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = if (isSystemInDarkTheme()) {
                        MaterialTheme.colors.background
                    } else {
                        MaterialTheme.colors.surface
                    }
                )
                .padding(
                    top = ExtraLargeSpacing + LargeSpacing,
                    start = LargeSpacing + MediumSpacing,
                    end = LargeSpacing + MediumSpacing,
                    bottom = LargeSpacing
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            CustomTextField(
                value = uiState.email ?: "",
                onValueChange = onEmailChange,
                hint = R.string.email_hint,
                keyboardType = KeyboardType.Email
            )
            CustomTextField(
                value = uiState.password ?: "",
                onValueChange = onPasswordChange,
                hint = R.string.password_hint,
                keyboardType = KeyboardType.Password,
                isPasswordTextField = true
            )
            CustomBtn(
                text = R.string.login_btn_hint,
                onClick = { onSignInClick() },
                modifier = modifier
                    .fillMaxWidth()
                    .height(ButtonHeight),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = DefaultElevation
                ),
                shape = MaterialTheme.shapes.medium
            )

            GotoSignUp { onNavigateSignUp() }
        }
    }

    if (uiState.isAuthenticating) {
        CircularProgressIndicator()
    }

    LaunchedEffect(
        key1 = uiState.authenticationSucceed,
        key2 = uiState.authErrorMessage,
        block = {
            if (uiState.authenticationSucceed) {
                onNavigateToHome.invoke()
            }

            if (uiState.authErrorMessage != null) {
                Toast.makeText(context, uiState.authErrorMessage, Toast.LENGTH_LONG).show()
            }
        })
}

@Composable
fun GotoSignUp(
    modifier: Modifier = Modifier,
    onNavigateSignUp: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(text = "Don't have an account?", style = MaterialTheme.typography.subtitle1)
        Text(text = "SignUp",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primary,
            modifier = modifier.clickable { onNavigateSignUp.invoke() }
        )
    }
}