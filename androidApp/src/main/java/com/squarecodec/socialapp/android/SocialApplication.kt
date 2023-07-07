package com.squarecodec.socialapp.android

import android.app.Application
import com.squarecodec.socialapp.android.di.modulesOfAndroid
import com.squarecodec.socialapp.di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialApplication)
            modules(modulesOfAndroid() + getSharedModules())
        }
    }
}