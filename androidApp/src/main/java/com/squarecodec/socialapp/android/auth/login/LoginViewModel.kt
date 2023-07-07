package com.squarecodec.socialapp.android.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squarecodec.socialapp.android.common.datastore.UserSettings
import com.squarecodec.socialapp.android.common.datastore.toAuthResultData
import com.squarecodec.socialapp.android.common.datastore.toUserSettings
import com.squarecodec.socialapp.auth.domain.usecase.SignInUseCase
import com.squarecodec.socialapp.common.util.Result
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInUseCase: SignInUseCase,
    private val dataStore: DataStore<UserSettings>
) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun signIn() {
        viewModelScope.launch {
            uiState = uiState.copy(isAuthenticating = true)

            val authResulData = signInUseCase.invoke(
                uiState.email ?: "",
                uiState.password ?: ""
            )
            uiState = when (authResulData) {
                is Result.Error -> {
                    uiState.copy(
                        isAuthenticating = false,
                        authErrorMessage = authResulData.message
                    )
                }

                is Result.Success -> {
                    dataStore.updateData {
                        authResulData.data?.toUserSettings() ?: UserSettings()
                    }
                    uiState.copy(
                        isAuthenticating = false,
                        authenticationSucceed = true,
                    )
                }
            }
        }

    }

    fun updateEmail(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }
}

data class LoginUiState(
    var email: String? = null,
    var password: String? = null,
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)