package com.tatsuki.fireframe

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import com.tatsuki.fireframe.core.testing.util.HiltAndroidAutoInjectRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(
    application = HiltTestApplication::class,
    qualifiers = RobolectricDeviceQualifiers.MediumTablet,
)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltAndroidAutoInjectRule = HiltAndroidAutoInjectRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun capture_robo_image_sample() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(ViewMatchers.isRoot())
            .captureRoboImage()
    }
}

class MainActivityRobot @Inject constructor()
