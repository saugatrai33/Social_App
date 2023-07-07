package com.squarecodec.socialapp.common.util

import com.squarecodec.socialapp.common.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class AndroidDispatcher: Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}

//internal actual fun provideDispatcher(): Dispatcher = AndroidDispatcher()