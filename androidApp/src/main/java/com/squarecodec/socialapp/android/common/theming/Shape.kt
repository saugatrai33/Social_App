package com.squarecodec.socialapp.android.common.theming

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import com.squarecodec.socialapp.android.common.theming.LargeSpacing
import com.squarecodec.socialapp.android.common.theming.MediumSpacing
import com.squarecodec.socialapp.android.common.theming.SmallSpacing

val Shapes = Shapes(
    small = RoundedCornerShape(SmallSpacing),
    medium = RoundedCornerShape(MediumSpacing),
    large = RoundedCornerShape(LargeSpacing)
)