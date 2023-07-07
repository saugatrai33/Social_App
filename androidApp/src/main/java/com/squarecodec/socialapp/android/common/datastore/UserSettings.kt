package com.squarecodec.socialapp.android.common.datastore

import com.squarecodec.socialapp.auth.domain.model.AuthResulData
import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val id: Int = -1,
    val name: String = "",
    val bio: String = "",
    val avatar: String? = null,
    val token: String = "",
    val followersCount: Int = 0,
    val followingCount: Int = 0
)

fun UserSettings.toAuthResultData(): AuthResulData {
    return AuthResulData(id, name, bio, avatar, token, followersCount, followingCount)
}

fun AuthResulData.toUserSettings(): UserSettings {
    return UserSettings(id, name, bio, avatar, token, followersCount, followingCount)
}