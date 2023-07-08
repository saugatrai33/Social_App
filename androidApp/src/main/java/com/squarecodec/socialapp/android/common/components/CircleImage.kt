package com.squarecodec.socialapp.android.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.squarecodec.socialapp.android.R

@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    onClick: () -> Unit
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick.invoke() },
        placeholder = if (MaterialTheme.colors.isLight) {
            painterResource(id = R.drawable.light_image_place_holder)
        } else {
            painterResource(id = R.drawable.dark_image_place_holder)
        },
        contentScale = ContentScale.Crop
    )
}