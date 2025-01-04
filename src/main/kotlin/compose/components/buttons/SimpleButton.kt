package compose.components.buttons

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleButton (
    modifier: Modifier,
    text: String,
    isActive: Boolean,
    fn: () -> Unit
) {
    MaterialTheme {
        Button(
            modifier = modifier,
            onClick = { fn() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isActive) {
                    MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                } else {
                    MaterialTheme.colors.primary
                },
            ),
        ) {
            Text(text = text)
        }
    }
}