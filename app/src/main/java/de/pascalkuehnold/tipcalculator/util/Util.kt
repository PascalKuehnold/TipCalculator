package de.pascalkuehnold.tipcalculator.util


/**
 * Created by Pascal Kühnold on 7/14/2022.
 */

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