package com.squarecodec.socialapp.auth.data

import com.squarecodec.socialapp.auth.domain.model.AuthResulData
import com.squarecodec.socialapp.auth.domain.repository.AuthRepository
import com.squarecodec.socialapp.common.Dispatcher
import com.squarecodec.socialapp.common.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
//    private val dispatcherProvider: Dispatcher,
    private val dispatcherProvider: CoroutineDispatcher = Dispatchers.Default,
    private val authService: AuthService
) : AuthRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResulData> {
        return withContext(dispatcherProvider) {
            try {
                val response = authService.signUp(SignUpRequest(name, email, password))
                if (response.data == null) {
                    Result.Error(message = response.errorMessage)
                } else {
                    Result.Success(data = response.data.toAuthResultData())
                }
            } catch (e: Exception) {
                Result.Error(message = e.message)
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResulData> {
        return withContext(dispatcherProvider) {
            try {
                val response = authService.signIn(SignInRequest(email, password))
                if (response.data == null) {
                    Result.Error(message = response.errorMessage)
                } else {
                    Result.Success(data = response.data.toAuthResultData())
                }
            } catch (e: Exception) {
                Result.Error(message = e.message)
            }
        }
    }
}