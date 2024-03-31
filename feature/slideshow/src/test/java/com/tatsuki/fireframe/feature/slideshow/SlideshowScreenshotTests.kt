package com.tatsuki.fireframe.feature.slideshow

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import com.tatsuki.fireframe.feature.slideshow.ui.SlideshowScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class SlideshowScreenshotTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun capture_slideshow_screen() {
        composeTestRule.setContent {
            SlideshowScreen()
        }

        composeTestRule
            .onRoot()
            .captureRoboImage()
    }
}
