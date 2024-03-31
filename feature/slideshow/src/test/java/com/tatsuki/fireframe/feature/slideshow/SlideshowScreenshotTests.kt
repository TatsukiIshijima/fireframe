package com.tatsuki.fireframe.feature.slideshow

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.RoborazziOptions.CompareOptions
import com.github.takahirom.roborazzi.captureRoboImage
import com.tatsuki.fireframe.core.testing.DefaultTestDevices
import com.tatsuki.fireframe.core.testing.captureForDevice
import com.tatsuki.fireframe.feature.slideshow.ui.SlideshowScreen
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(application = HiltTestApplication::class)
class SlideshowScreenshotTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val roborazziOptions = RoborazziOptions(
        compareOptions = CompareOptions(changeThreshold = 0f),
        recordOptions = RoborazziOptions.RecordOptions(resizeScale = 0.5),
    )

    @Test
    fun capture_slideshow_screen() {
        composeTestRule.setContent {
            Hoge()
        }

        composeTestRule
            .onRoot()
            .captureRoboImage(
                roborazziOptions = roborazziOptions,
            )
    }

    @Composable
    private fun Hoge() {
        MaterialTheme {
            Surface {
                SlideshowScreen()
            }
        }
    }
}
