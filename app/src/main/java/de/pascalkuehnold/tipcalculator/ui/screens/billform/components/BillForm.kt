package de.pascalkuehnold.tipcalculator.ui.screens.billform.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.pascalkuehnold.tipcalculator.domain.BillFormViewModel
import de.pascalkuehnold.tipcalculator.shared.widgets.InputField
import de.pascalkuehnold.tipcalculator.shared.widgets.RoundIconButton

/**
 * Created by Pascal Kühnold on 7/13/2022.
 */

/**
 * Composable function for the BillForm
 * It represents the main screen of this app
 *
 * @param modifier  The modifier to be applied to the BillForm.
 * @param range The range value of the percentage slider
 * @param onValChange Function which is called when changing values
 * @param viewModel The viewModel for the composable
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    range: IntRange = (1..100 step 5) as IntRange,
    onValChange: (String) -> Unit = {},
    viewModel: BillFormViewModel = viewModel()
) {

    val totalBillState = remember {
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    /**
     * The position state of the slider
     */
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    /**
     * Calculating the Tip Percentage by using the sliderPositionState * 100
     */
    val tipPercentage = (sliderPositionState.value * 100).toInt()

    /**
     * Getting the LocalSoftwareKeyboardController for hiding the keyboard
     */
    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader(
        totalPerPerson = viewModel.totalPerPersonState,

        totalBill = viewModel.totalBillAmountWithTip
    )

    Surface(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(all = CornerSize(8.dp)),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )
    ) {
        Column(
            modifier = modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                modifier = modifier.fillMaxWidth(),
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if(!validState) return@KeyboardActions

                    onValChange(totalBillState.value.trim())

                    keyboardController?.hide()

                }
            )
            if(validState){
                viewModel.totalPerPersonState =
                    viewModel.calculateTotalPerPerson(
                        totalBill = totalBillState.value.toDouble(),
                        personsToSplitBy = viewModel.splitByState,
                        tipPercentage = tipPercentage
                    )
                viewModel.totalBillAmountWithTip =
                    viewModel.getTotalBillAmountPlusTip(
                        totalBill = totalBillState.value.toDouble(),
                        tipPercentage = tipPercentage
                    )

                Row(
                    modifier = modifier
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Split",
                        modifier = modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = modifier.width(120.dp))

                    Row(
                        modifier = modifier
                            .padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {
                                viewModel.splitByState =
                                    if(viewModel.splitByState > range.first){
                                        viewModel.splitByState - 1
                                    } else {
                                        1
                                    }
                                viewModel.totalPerPersonState =
                                    viewModel.calculateTotalPerPerson(
                                        totalBill = totalBillState.value.toDouble(),
                                        personsToSplitBy = viewModel.splitByState,
                                        tipPercentage = tipPercentage
                                    )
                            }
                        )

                        Text(
                            text = "${viewModel.splitByState}",
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .align(Alignment.CenterVertically)
                        )

                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                                if( viewModel.splitByState < range.last){
                                    viewModel.splitByState += 1
                                }
                                viewModel.totalPerPersonState =
                                    viewModel.calculateTotalPerPerson(
                                        totalBill = totalBillState.value.toDouble(),
                                        personsToSplitBy =  viewModel.splitByState,
                                        tipPercentage = tipPercentage
                                    )

                            }
                        )
                    }
                }
                //Tip Row
                Row (
                    modifier = modifier
                        .padding(horizontal = 3.dp, vertical = 12.dp)
                ){
                    Text(
                        text = "Tip",
                        modifier = modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = modifier.width(200.dp))

                    Text(
                        text = "$ ${ viewModel.tipAmountState}",
                        modifier = modifier.align(alignment = Alignment.CenterVertically)
                    )

                }

                //Percentage Row
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$tipPercentage %"
                    )

                    Spacer(modifier = modifier.height(14.dp))
                    //Slider
                    Slider(
                        value = sliderPositionState.value,
                        steps = (range.last / 5) - 1,
                        onValueChange = {newVal ->

                            sliderPositionState.value = newVal

                            viewModel.tipAmountState = viewModel.calculateTotalTip(
                                totalBill = totalBillState.value.toDouble(),
                                tipPercentage = tipPercentage
                            )

                            viewModel.totalPerPersonState =
                                viewModel.calculateTotalPerPerson(
                                    totalBill = totalBillState.value.toDouble(),
                                    personsToSplitBy =  viewModel.splitByState,
                                    tipPercentage = tipPercentage
                                )

                        },
                        modifier = modifier.padding(horizontal = 16.dp)

                    )
                }
            }
        }
    }
}


