package com.squarecodec.socialapp.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.squarecodec.socialapp.android.MainViewModel
import com.squarecodec.socialapp.android.auth.login.LoginViewModel
import com.squarecodec.socialapp.android.auth.signup.SignUpViewModel
import com.squarecodec.socialapp.android.common.datastore.UserSettingSerializer
import com.squarecodec.socialapp.android.common.util.DATASTORE_FILENAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }

    single {
        DataStoreFactory.create(
            serializer = UserSettingSerializer,
            produceFile = {
                androidContext().dataStoreFile(
                    fileName = DATASTORE_FILENAME
                )
            }
        )
    }
}

private val androidModules = listOf(appModule)

internal fun modulesOfAndroid() = androidModules
