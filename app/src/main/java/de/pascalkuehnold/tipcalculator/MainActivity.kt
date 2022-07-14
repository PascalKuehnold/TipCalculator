package de.pascalkuehnold.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.pascalkuehnold.tipcalculator.ui.screens.billform.components.BillForm
import de.pascalkuehnold.tipcalculator.ui.screens.billform.components.MainContent
import de.pascalkuehnold.tipcalculator.ui.screens.billform.components.TopHeader
import de.pascalkuehnold.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent{
                TopHeader()
                BillForm()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent{
        TopHeader()
        BillForm()
    }
}

