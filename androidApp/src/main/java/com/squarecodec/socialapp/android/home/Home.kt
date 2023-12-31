package com.squarecodec.socialapp.android.home

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.squarecodec.socialapp.android.destinations.PostDetailDestination
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = true)
fun Home(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeScreenViewModel = koinViewModel()

    HomeScreen(
        onBoardingUiState = viewModel.onBoardingUiState,
        postsUiState = viewModel.postUiState,
        onPostClick = {
            navigator.navigate(PostDetailDestination(it.id))
        },
        onProfileClick = {},
        onLikeClick = { /*TODO*/ },
        onCommentClick = { /*TODO*/ },
        onFollowButtonClick = { _, _ -> },
        onBoardingFinish = { /*TODO*/ },
        fetchData = {
            viewModel.fetchData()
        }
    )
}