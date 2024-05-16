package com.tatsuki.fireframe.core.common

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.View
import android.view.Window
import android.view.WindowInsets

fun Window.setVisibleSystemBars(isVisible: Boolean) {
    if (VERSION.SDK_INT >= VERSION_CODES.R) {
        if (isVisible) {
            insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            insetsController?.hide(WindowInsets.Type.statusBars())
        }
    } else {
        if (isVisible) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        } else {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}
