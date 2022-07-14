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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.pascalkuehnold.tipcalculator.shared.widgets.InputField
import de.pascalkuehnold.tipcalculator.shared.widgets.RoundIconButton
import de.pascalkuehnold.tipcalculator.util.calculateTotalTip


/**
 * Created by Pascal Kühnold on 7/13/2022.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {}
) {
    val range = IntRange(start = 1, endInclusive = 100)
    val tipAmountState = remember {
        mutableStateOf(0.0)
    }

    val totalPersonState = remember {
        mutableStateOf(1)
    }

    val totalBillState = remember {
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }



    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val tipPercentage = (sliderPositionState.value * 100).toInt()


    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(all = CornerSize(8.dp)),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                modifier = Modifier.fillMaxWidth(),
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
            //if(validState){
                Row(
                    modifier = Modifier
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Split",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(120.dp))

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {
                               totalPersonState.value =
                                   if(totalPersonState.value > range.first){
                                       totalPersonState.value - 1
                                   } else {
                                       1
                                   }
                            }
                        )

                        Text(
                            text = "${totalPersonState.value}",
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .align(Alignment.CenterVertically)
                        )

                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                                if(totalPersonState.value < range.last){
                                    totalPersonState.value += 1
                                }
                            }
                        )
                    }
                }
            //Tip Row
            Row (
                modifier = Modifier
                    .padding(horizontal = 3.dp, vertical = 12.dp)
            ){
                Text(
                    text = "Tip",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(200.dp))

                Text(
                    text = "$ ${tipAmountState.value}",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
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

                Spacer(modifier = Modifier.height(14.dp))
                //Slider
                Slider(
                    value = sliderPositionState.value,
                    onValueChange = {newVal ->
                        sliderPositionState.value = newVal
                        tipAmountState.value = calculateTotalTip(
                            totalBill = totalBillState.value.toDouble(),
                            tipPercentage = tipPercentage
                        )
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    steps = 5

                )
            }

//            } else {
//                Box(){
//
//                }
//            }
        }
    }
}


