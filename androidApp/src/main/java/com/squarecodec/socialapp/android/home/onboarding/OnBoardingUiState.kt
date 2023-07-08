package com.squarecodec.socialapp.android.home.onboarding

import com.squarecodec.socialapp.android.common.fake_data.FollowsUser

data class OnBoardingUiState(
    val isLoading: Boolean = false,
    val users: List<FollowsUser> = emptyList(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)
