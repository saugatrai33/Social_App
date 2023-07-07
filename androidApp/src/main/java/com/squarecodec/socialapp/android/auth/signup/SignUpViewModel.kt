package com.squarecodec.socialapp.android.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squarecodec.socialapp.android.common.datastore.UserSettings
import com.squarecodec.socialapp.android.common.datastore.toUserSettings
import com.squarecodec.socialapp.auth.domain.usecase.SignUpUseCase
import com.squarecodec.socialapp.common.util.Result
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val dataStore: DataStore<UserSettings>
) : ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun updateUserName(newUserName: String) {
        uiState = uiState.copy(userName = newUserName)
    }

    fun updateEmail(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    fun signUp() {
        viewModelScope.launch {
            uiState = uiState.copy(isAuthenticating = true)
            val authResulData = signUpUseCase.invoke(
                uiState.email ?: "",
                uiState.userName ?: "",
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
}

data class SignUpUiState(
    var userName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)