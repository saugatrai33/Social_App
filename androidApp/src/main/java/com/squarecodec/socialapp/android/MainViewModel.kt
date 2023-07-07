package com.squarecodec.socialapp.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.squarecodec.socialapp.android.common.datastore.UserSettings
import com.squarecodec.socialapp.android.common.datastore.toAuthResultData
import kotlinx.coroutines.flow.map

internal class MainViewModel(
    private val dataStore: DataStore<UserSettings>
) : ViewModel(){
    val authState = dataStore.data.map { it.toAuthResultData().token }
}