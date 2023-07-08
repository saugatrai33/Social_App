package com.squarecodec.socialapp.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squarecodec.socialapp.android.common.fake_data.Post
import com.squarecodec.socialapp.android.common.fake_data.samplePosts
import com.squarecodec.socialapp.android.common.fake_data.sampleUsers
import com.squarecodec.socialapp.android.home.onboarding.OnBoardingUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    var postUiState by mutableStateOf(PostsUiState())
        private set

    var onBoardingUiState by mutableStateOf(OnBoardingUiState())
        private set

    init {
        fetchData()
    }

    fun fetchData() {
        onBoardingUiState = onBoardingUiState.copy(isLoading = true)
        postUiState = postUiState.copy(isLoading = true)

        viewModelScope.launch {
            delay(2000)
            onBoardingUiState = onBoardingUiState.copy(
                isLoading = false,
                users = sampleUsers,
                shouldShowOnBoarding = true
            )
            postUiState = postUiState.copy(
                isLoading = false,
                posts = samplePosts
            )
        }
    }
}

data class PostsUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val errorMessage: String? = null
)