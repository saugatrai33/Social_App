package com.squarecodec.socialapp.auth.domain.usecase

import com.squarecodec.socialapp.auth.domain.model.AuthResulData
import com.squarecodec.socialapp.auth.domain.repository.AuthRepository
import com.squarecodec.socialapp.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUseCase : KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        name: String,
        password: String
    ): Result<AuthResulData> {
        if (name.isBlank() || name.length < 3) {
            return Result.Error(message = "invalid name")
        }

        if (email.isBlank() || "@" !in email) {
            return Result.Error(message = "invalid email")
        }

        if (password.isBlank() || password.length < 4) {
            return Result.Error(message = "invalid password")
        }

        return repository.signUp(name, email, password)
    }
}