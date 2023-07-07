package com.squarecodec.socialapp.auth.domain.model

data class AuthResulData(
    val id: Int,
    val name: String,
    val bio: String,
    val avatar: String? = null,
    val token: String,
    val followersCount: Int = 0,
    val followingCount: Int = 0
)
