package com.squarecodec.socialapp.android.common.fake_data

data class Comment(
    val id: String = "",
    val comment: String = "",
    val date: String = "",
    val autherName: String = "",
    val autherImageUrl: String = "",
    val autherId: Int = -1,
    val postId: String = "",
)

internal val sampleComments = listOf(
    Comment(
        id = "1",
        comment = "Great post. learned a lot",
        date = "2023-01-01",
        autherName = sampleUsers[0].profileUrl,
        autherImageUrl = sampleUsers[0].profileUrl,
        autherId = sampleUsers[0].id,
        postId = samplePosts[0].id
    ),
    Comment(
        id = "2",
        comment = "Great post. learned a lot",
        date = "2023-01-01",
        autherName = sampleUsers[1].profileUrl,
        autherImageUrl = sampleUsers[1].profileUrl,
        autherId = sampleUsers[1].id,
        postId = samplePosts[1].id
    ),
    Comment(
        id = "3",
        comment = "Great post. learned a lot",
        date = "2023-01-01",
        autherName = sampleUsers[2].profileUrl,
        autherImageUrl = sampleUsers[2].profileUrl,
        autherId = sampleUsers[2].id,
        postId = samplePosts[2].id
    )
)