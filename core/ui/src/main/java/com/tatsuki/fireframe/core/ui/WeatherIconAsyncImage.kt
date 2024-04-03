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

package com.tatsuki.fireframe.core.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage

@Composable
fun WeatherIconAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeHolder: @Composable () -> Unit = {},
) {
    // Determine if the composable is being used in preview etc.
    val isLocalInspection = LocalInspectionMode.current

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
    ) {
        val state = painter.state
        if (isLocalInspection || state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            placeHolder()
        } else {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = modifier,
            )
        }
    }
}