package com.tatsuki.fireframe.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import com.tatsuki.fireframe.core.model.SlideGroup
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

    @Test
    fun capture_home_screen_disable_start_button_when_slide_group_empty() {
        composeTestRule.setContent {
            HomeScreen(
                homeState = HomeState.fake(
                    slideGroups = emptyList(),
                ),
            )
        }

        composeTestRule
            .onRoot()
            .captureRoboImage()
    }

    @Test
    fun capture_home_screen_disable_source_buttons_when_slide_group_max() {
        composeTestRule.setContent {
            HomeScreen(
                homeState = HomeState.fake(
                    slideGroups = (0..10).map {
                        SlideGroup.fake(
                            id = it.toLong(),
                            groupName = "SlideGroup$it",
                        )
                    },
                ),
            )
        }

        composeTestRule
            .onRoot()
            .captureRoboImage()
    }
}
