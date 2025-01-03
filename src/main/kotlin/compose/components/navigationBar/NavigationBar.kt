package compose.components.navigationBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import compose.components.navButton.NavButton

@Composable
fun NavigationBar(
    navController: NavHostController
) {
    MaterialTheme {
        BottomAppBar(
            windowInsets = AppBarDefaults.bottomAppBarWindowInsets
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val weight40 = Modifier.weight(0.4f)
                NavButton(weight40, "deals", "Deals", navController)
                NavButton(Modifier.weight(0.2f), "add", "Add", navController)
                NavButton(weight40, "all", "All", navController)
            }
        }
    }
}