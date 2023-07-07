package com.squarecodec.socialapp.android.common.components

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource

@Composable
fun CustomBtn(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    elevation: ButtonElevation? = null,
    shape: Shape = MaterialTheme.shapes.medium
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        elevation = elevation,
        shape = shape
    ) {
        Text(text = stringResource(id = text))
    }
}