package com.squarecodec.socialapp.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.squarecodec.socialapp.android.common.components.PostListItem
import com.squarecodec.socialapp.android.common.fake_data.FollowsUser
import com.squarecodec.socialapp.android.common.fake_data.Post
import com.squarecodec.socialapp.android.home.onboarding.OnBoardingSection
import com.squarecodec.socialapp.android.home.onboarding.OnBoardingUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBoardingUiState: OnBoardingUiState,
    postsUiState: PostsUiState,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
    onBoardingFinish: () -> Unit,
    fetchData: () -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = onBoardingUiState.isLoading && postsUiState.isLoading,
        onRefresh = { fetchData() }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state = pullRefreshState)
    ) {

        LazyColumn(
            modifier = modifier.fillMaxSize()
        ){

            if (onBoardingUiState.shouldShowOnBoarding){
                item(key = "onboardingsection") {
                    OnBoardingSection(
                        users = onBoardingUiState.users,
                        onUserClick = {onProfileClick(it.id)},
                        onFollowButtonClick = onFollowButtonClick
                    ) {
                        onBoardingFinish()
                    }
                }
            }

            items(items = postsUiState.posts, key = {post -> post.id}){newPost ->
                PostListItem(
                    post = newPost,
                    onPostClick = { onPostClick.invoke(newPost) },
                    onProfileClick = onProfileClick,
                    onLikeClick = onLikeClick,
                    onCommentClick = onCommentClick
                )
            }

        }

        PullRefreshIndicator(
            refreshing = onBoardingUiState.isLoading && postsUiState.isLoading,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )

    }

}