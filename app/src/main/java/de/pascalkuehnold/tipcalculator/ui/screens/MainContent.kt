package de.pascalkuehnold.tipcalculator.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import de.pascalkuehnold.tipcalculator.domain.BillFormViewModel
import de.pascalkuehnold.tipcalculator.ui.screens.billform.components.BillForm
import de.pascalkuehnold.tipcalculator.ui.theme.TipCalculatorTheme

/**
 * Created by Pascal Kühnold on 7/14/2022.
 */

/**
 * Composable Function to display the main content
 *
 * @property range The range for the tip slider
 */
@Composable
fun MainContent() {
    val range = IntRange(start = 1, endInclusive = 100)
    val billFormViewModel = BillFormViewModel()

    TipCalculatorTheme() {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                BillForm(
                    range = range,
                    viewModel = billFormViewModel
                )
            }
        }
    }
}