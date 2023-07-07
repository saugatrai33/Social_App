package com.squarecodec.socialapp.di

import com.squarecodec.socialapp.auth.data.AuthRepositoryImpl
import com.squarecodec.socialapp.auth.data.AuthService
import com.squarecodec.socialapp.auth.domain.repository.AuthRepository
import com.squarecodec.socialapp.auth.domain.usecase.SignInUseCase
import com.squarecodec.socialapp.auth.domain.usecase.SignUpUseCase
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(authService = get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
//    factory { provideDispatcher() }
}

fun getSharedModules() = listOf(authModule, utilityModule)