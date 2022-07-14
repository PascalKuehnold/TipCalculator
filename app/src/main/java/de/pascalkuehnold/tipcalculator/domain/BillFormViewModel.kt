package de.pascalkuehnold.tipcalculator.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.ViewModel


/**
 * Created by Pascal Kühnold on 7/14/2022.
 */

class BillFormViewModel: ViewModel() {

    var tipAmountState by mutableStateOf(0.0)
    var splitByState by mutableStateOf(1)
    var totalPerPersonState by mutableStateOf(0.0)
    var sliderPositionState by mutableStateOf(0f)
    var tipPercentage = (sliderPositionState * 100).toInt()


    fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
        return if(
            totalBill > 1 &&
            totalBill.toString().isNotEmpty()
        )
        {
            (totalBill * tipPercentage) / 100
        } else {
            0.0
        }
    }

    fun calculateTotalPerPerson(
        totalBill: Double,
        personsToSplitBy: Int,
        tipPercentage: Int
    ): Double{
        val bill = calculateTotalTip(
            totalBill = totalBill,
            tipPercentage = tipPercentage
        ) + totalBill

        return (bill / personsToSplitBy)

    }

}