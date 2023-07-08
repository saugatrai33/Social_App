package com.squarecodec.socialapp.android.home.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.squarecodec.socialapp.android.R
import com.squarecodec.socialapp.android.common.components.CircleImage
import com.squarecodec.socialapp.android.common.components.FollowsButton
import com.squarecodec.socialapp.android.common.fake_data.FollowsUser
import com.squarecodec.socialapp.android.common.theming.MediumSpacing
import com.squarecodec.socialapp.android.common.theming.SmallSpacing

@Composable
fun OnBoardingUserItem(
    modifier: Modifier = Modifier,
    followsUser: FollowsUser,
    onUserClick: (FollowsUser) -> Unit,
    isFollowing: Boolean = false,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit
) {
    Card(
        modifier = modifier
            .height(140.dp)
            .width(130.dp)
            .clickable { onUserClick.invoke(followsUser) },
        elevation = 0.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(MediumSpacing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleImage(
                imageUrl = followsUser.profileUrl,
                modifier = modifier.size(50.dp)
            ) {
                onUserClick.invoke(followsUser)
            }
            Spacer(modifier = modifier.height(SmallSpacing))
            Text(
                text = followsUser.name, style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.height(MediumSpacing))
            FollowsButton(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = 30.dp),
                text = R.string.follow_button_text,
                onClick = { onFollowButtonClick.invoke(!isFollowing, followsUser) })
        }
    }
}