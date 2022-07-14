package de.pascalkuehnold.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.pascalkuehnold.tipcalculator.ui.screens.MainContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}

