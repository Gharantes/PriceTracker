package compose.components.buttons

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable

@Composable
fun NavBarAddButton (
    currentRoute: String,
    navigateToAddGamePageFn: () -> Unit,
    addGameToListFn: () -> Unit,
) {
    MaterialTheme {
        Button(
            onClick = if (currentRoute == "add") addGameToListFn else navigateToAddGamePageFn
        ) {
            if ("add" == currentRoute) {
                Icon(Icons.Filled.Check, contentDescription = "Add")
            } else {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}