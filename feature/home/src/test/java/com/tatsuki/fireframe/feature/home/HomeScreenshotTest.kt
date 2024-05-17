package com.tatsuki.fireframe.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import com.tatsuki.fireframe.feature.home.model.HomeState
import com.tatsuki.fireframe.feature.home.ui.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class HomeScreenshotTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun capture_home_screen() {
        composeTestRule.setContent {
            HomeScreen(
                homeState = HomeState.fake(),
            )
        }

        composeTestRule
            .onRoot()
            .captureRoboImage()
    }
}
