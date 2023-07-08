package com.squarecodec.socialapp.android.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.squarecodec.socialapp.android.R
import com.squarecodec.socialapp.android.common.fake_data.Comment
import com.squarecodec.socialapp.android.common.theming.DarkGray
import com.squarecodec.socialapp.android.common.theming.LargeSpacing
import com.squarecodec.socialapp.android.common.theming.LightGray
import com.squarecodec.socialapp.android.common.theming.MediumSpacing

@Composable
fun CommentListItem(
    modifier: Modifier = Modifier,
    comment: Comment,
    onProfileClick: (Int) -> Unit,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = LargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleImage(imageUrl = comment.autherImageUrl, modifier = modifier.size(30.dp)) {
            onProfileClick.invoke(comment.autherId)
        }
        Column(modifier = modifier.weight(1f)) {
            Row(horizontalArrangement = Arrangement.spacedBy(MediumSpacing)) {
                Text(
                    text = comment.autherName,
                    modifier = modifier.alignByBaseline(),
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = comment.date,
                    modifier = modifier
                        .alignByBaseline()
                        .weight(1f),
                    style = MaterialTheme.typography.caption,
                    color = if (MaterialTheme.colors.isLight) {
                        LightGray
                    } else {
                        DarkGray
                    }
                )
                Icon(
                    painter = painterResource(id = R.drawable.round_more_horiz_24),
                    contentDescription = null,
                    tint = if (MaterialTheme.colors.isLight) {
                        LightGray
                    } else {
                        DarkGray
                    },
                    modifier = modifier.clickable { onMoreClick.invoke() }
                )
            }
            Text(text = comment.comment, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun CommentSectionHeader(
    modifier: Modifier = Modifier,
    onAddNewCommentClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.comments_header_label),
            style = MaterialTheme.typography.subtitle1
        )
        OutlinedButton(onClick = onAddNewCommentClick) {
            Text(
                text = stringResource(id = R.string.add_comment_btn_label),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}