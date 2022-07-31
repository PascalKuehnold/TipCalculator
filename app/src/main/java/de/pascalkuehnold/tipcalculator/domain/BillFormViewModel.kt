package de.pascalkuehnold.tipcalculator.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


/**
 * Created by Pascal Kühnold on 7/14/2022.
 */

/**
 * The Viemodel for the BillForm
 *
 * @property tipAmountState The Tip amount
 * @property splitByState The total amount of persons to split the bill by
 * @property totalPerPersonState The total amount that a person must pay
 * @property sliderPositionState Represents the position of the Slider
 * @property tipPercentage Represents the tip percentage amount
 *
 */
class BillFormViewModel: ViewModel() {

    var tipAmountState by mutableStateOf(0.0)
    var splitByState by mutableStateOf(1)
    var totalPerPersonState by mutableStateOf(0.0)
    var sliderPositionState by mutableStateOf(0f)

    var totalBillAmountWithTip by mutableStateOf(0.0)

    /**
     * Function to calculate the Total Tip
     *
     * @param totalBill The overall amount of the bill
     * @param tipPercentage The Tip percentage to calculate with
     * @return The Total tip amount
     */
    fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
        return if(
            totalBill > 1 &&
            totalBill.toString().isNotEmpty()
        )
        {
            (totalBill * tipPercentage) / 100
        }  else {
            0.0
        }
    }

    /**
     * Function to calculate the total pay amount for each person
     *
     * @param totalBill The overall amount of the bill
     * @param personsToSplitBy The persons to split by
     * @param tipPercentage The Tip percentage
     * @return The amount which each person must pay
     */
    fun calculateTotalPerPerson(
        totalBill: Double,
        personsToSplitBy: Int,
        tipPercentage: Int
    ): Double{
        val bill = getTotalBillAmountPlusTip(
            totalBill = totalBill,
            tipPercentage = tipPercentage
        )

        return (bill / personsToSplitBy)

    }

    fun getTotalBillAmountPlusTip(
        totalBill: Double,
        tipPercentage: Int
    ): Double {

        return calculateTotalTip(
            totalBill = totalBill,
            tipPercentage = tipPercentage
        ) + totalBill
    }

}