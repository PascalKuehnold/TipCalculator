package de.pascalkuehnold.tipcalculator.shared.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * Created by Pascal Kühnold on 7/13/2022.
 */

//Variable for the Size of the button
val IconButtonSizeModifier = Modifier.size(40.dp)

/**
 * This composable function is responsible for creating rounded buttons
 *
 * @param modifier for customizing, using the default one
 * @param imageVector for displaying an Icon or Image for the button
 * @param onClick lambda function for firing the desired logic for the button
 * @param tint variable for the color of the icon
 * @param backgroundColor the background color for the button
 * @param elevation
 */
@Composable
fun RoundIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 4.dp
) {
    Card(
        modifier = modifier
            .padding(all = 4.dp)
            .clickable {
                onClick.invoke()
            }
            .then(IconButtonSizeModifier),
        shape = CircleShape,
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Plus or Minus icon",
            tint = tint
        )
    }
}