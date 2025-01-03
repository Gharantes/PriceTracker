package compose.components.navButton

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun NavButton (
    modifier: Modifier,
    route: String,
    text: String,
    navController: NavHostController
) {
    MaterialTheme {
        Button(
            modifier = modifier,
            onClick = {
                navController.navigate(
                    route = route
                )
            }
        ) {
            Text(text = text)
        }
    }
}