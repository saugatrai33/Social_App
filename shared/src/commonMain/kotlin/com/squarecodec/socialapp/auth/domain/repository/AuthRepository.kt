package com.squarecodec.socialapp.auth.domain.repository

import com.squarecodec.socialapp.auth.domain.model.AuthResulData
import com.squarecodec.socialapp.common.util.Result

internal interface AuthRepository {
    suspend fun signUp(name: String, email: String, password: String): Result<AuthResulData>

    suspend fun signIn(email: String, password: String): Result<AuthResulData>
}