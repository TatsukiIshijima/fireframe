/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// https://github.com/android/nowinandroid/blob/main/core/designsystem/src/main/kotlin/com/google/samples/apps/nowinandroid/core/designsystem/component/DynamicAsyncImage.kt

package com.tatsuki.fireframe.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter.State.Empty
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.AsyncImagePainter.State.Success
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun AsyncImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit = {},
    error: @Composable () -> Unit = {},
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(model)
        .size(Size.ORIGINAL)
        .build()
    val painter = rememberAsyncImagePainter(
        model = imageRequest,
    )

    when (painter.state) {
        Empty -> {}
        is Error -> {
            error()
        }

        is Loading -> {
            placeholder()
        }

        is Success -> {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter,
            )
        }
    }
}
