package de.pascalkuehnold.tipcalculator.shared.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Pascal Kühnold on 7/13/2022.
 */

/**
 * Composable function for a shared widget which creates an Inputfield
 *
 * @param modifier The modifier to be applied to the InputField
 * @param valueState The state of the text within the InputField
 * @param labelId The title for the Label
 * @param enabled Boolean if the inputfield is enabled or not
 * @param isSingleLine Boolean to make the inputField singleLine or not
 * @param keyboardType The Keyboard type for the input field
 * @param imeAction The Action displayed on the internal keyboard controller
 * @param onAction The actions that will be triggered in response to the users actions on the keyboard
 */
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = {
            Text(
                text = labelId
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.AttachMoney,
                contentDescription = "Money Icon",
            )},
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier.padding(
            bottom = 10.dp,
            start = 10.dp,
            end = 10.dp
        ),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = onAction
    )
}