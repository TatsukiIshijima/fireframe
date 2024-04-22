package com.tatsuki.fireframe.feature.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tatsuki.fireframe.feature.home.R

sealed interface SourceType {
    val nameResourceId: Int
    val iconResourceId: Int

    data class LocalStorage(
        @StringRes override val nameResourceId: Int = R.string.local_storage_name,
        @DrawableRes override val iconResourceId: Int = R.drawable.baseline_phone_android_24,
    ) : SourceType
}
