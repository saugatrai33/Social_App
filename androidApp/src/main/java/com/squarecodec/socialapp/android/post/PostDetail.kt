package com.squarecodec.socialapp.android.post

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun PostDetail(navigator: DestinationsNavigator, postId: String) {
    val viewModel: PostDetailScreenViewModel = koinViewModel()
    PostDetailScreen(
        postUiState = viewModel.postUiState,
        commentsUiState = viewModel.commentsUiState,
        onCommentMoreClick = {},
        onProfileClick = {},
        onAddCommentClick = { /*TODO*/ }) {
        viewModel.fetchData(postId)
    }
}