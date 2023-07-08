package com.squarecodec.socialapp.android.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.squarecodec.socialapp.android.R
import com.squarecodec.socialapp.android.common.components.CommentListItem
import com.squarecodec.socialapp.android.common.components.CommentSectionHeader
import com.squarecodec.socialapp.android.common.components.PostListItem
import com.squarecodec.socialapp.android.common.fake_data.Comment
import com.squarecodec.socialapp.android.common.fake_data.sampleComments
import com.squarecodec.socialapp.android.common.theming.LargeSpacing

@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    postUiState: PostUiState,
    commentsUiState: CommentsUiState,
    onCommentMoreClick: (Comment) -> Unit,
    onProfileClick: (Int) -> Unit,
    onAddCommentClick: () -> Unit,
    fetchData: () -> Unit
) {
    if (postUiState.isLoading && commentsUiState.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (postUiState.post != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface)
        ) {
            item(key = "post_list_item") {
                PostListItem(
                    post = postUiState.post,
                    onPostClick = { /*TODO*/ },
                    onProfileClick = onProfileClick,
                    onCommentClick = { },
                    onLikeClick = { /*TODO*/ },
                    isDetailScreen = true
                )
            }
            item(key = "comments_header_section") {
                CommentSectionHeader {
                    onAddCommentClick.invoke()
                }
            }
            items(items = sampleComments, key = { comment: Comment -> comment.id }) {
                Divider()
                CommentListItem(comment = it, onProfileClick = onProfileClick) {
                    onCommentMoreClick.invoke(it)
                }
            }
        }
    } else {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(verticalArrangement = Arrangement.spacedBy(LargeSpacing)) {
                Text(text = stringResource(id = R.string.loading_post_error))
                OutlinedButton(onClick = fetchData) {
                    Text(text = stringResource(id = R.string.retry_btn_label))
                }
            }
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        fetchData.invoke()
    })
}