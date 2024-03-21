package com.tatsuki.fireframe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tatsuki.fireframe.ui.FireframeApp
import com.tatsuki.fireframe.ui.rememberFireframeAppState
import com.tatsuki.fireframe.ui.theme.FireframeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberFireframeAppState()

            FireframeTheme {
                FireframeApp(appState)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FireframeTheme {
        Greeting("Android")
    }
}
