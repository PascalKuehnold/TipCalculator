package de.pascalkuehnold.tipcalculator.ui.screens.billform.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.pascalkuehnold.tipcalculator.ui.theme.TipCalculatorTheme


/**
 * Created by Pascal Kühnold on 7/14/2022.
 */
@Composable
@Preview
fun MainContent(content: @Composable () -> Unit = {}) {
    TipCalculatorTheme() {
        Surface(color = MaterialTheme.colors.background) {
           Column {
               content()
           }
        }
    }
}