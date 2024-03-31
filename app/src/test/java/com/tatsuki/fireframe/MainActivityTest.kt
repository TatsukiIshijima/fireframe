package com.tatsuki.fireframe

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
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
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun captureRoboImageSample() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(ViewMatchers.isRoot())
            .captureRoboImage()
    }

    @Test
    fun sampleTest() {
        composeTestRule.setContent {
            MaterialTheme {
                // Add your Composable here
                Text("Hello, World!")
            }
        }
        composeTestRule
            .onRoot()
            .captureRoboImage()
    }
}

class MainActivityRobot @Inject constructor() {

}
