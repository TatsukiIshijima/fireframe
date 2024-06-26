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

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import coil.size.Size
import com.tatsuki.fireframe.core.designsystem.R

@Composable
fun FireframeAsyncImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    @DrawableRes placeholderResource: Int? = R.drawable.outline_image_24,
    @DrawableRes errorResource: Int? = R.drawable.outline_error_outline_24,
) {
    val imageRequestParams = ImageRequest.Builder(LocalContext.current)
        .data(model)
        .size(Size.ORIGINAL)

    if (placeholderResource != null) {
        imageRequestParams.placeholder(placeholderResource)
    }
    if (errorResource != null) {
        imageRequestParams.error(errorResource)
    }

    val imageRequest = imageRequestParams.build()

    // Maintain image loading state to prevent recomposition.
    val data = rememberSaveable {
        imageRequest.data
    }

    coil.compose.AsyncImage(
        model = data,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
    )
}
