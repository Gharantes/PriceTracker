package compose.components.buttons

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleButton (
    modifier: Modifier,
    text: String,
    fn: () -> Unit
) {
    MaterialTheme {
        Button(
            modifier = modifier,
            onClick = { fn() }
        ) {
            Text(text = text)
        }
    }
}