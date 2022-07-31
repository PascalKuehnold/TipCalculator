package de.pascalkuehnold.tipcalculator.ui.screens.billform.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Pascal Kühnold on 7/13/2022.
 */


/**
 * Composable Function for the Topheader of the app
 *
 * @param totalPerPerson The amount which each person must pay
 */
@Composable
fun TopHeader(
    totalPerPerson: Double = 0.0,
    totalBill: Double = 0.0
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0xFFCBABF8)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val total = "%.2f".format(totalPerPerson)
            val _totalBill = "%.2f".format(totalBill)

            Text(
                text = "Total Person",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$$_totalBill",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Light
            )
        }
    }
}