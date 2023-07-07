package com.squarecodec.socialapp.auth.data

import com.squarecodec.socialapp.auth.domain.model.AuthResulData

internal fun AuthResponseData.toAuthResultData(): AuthResulData {
    return AuthResulData(id, name, bio, avatar, token, followersCount, followingCount)
}