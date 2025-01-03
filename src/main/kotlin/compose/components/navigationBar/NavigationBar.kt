package compose.components.navigationBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import compose.components.buttons.NavBarAddButton
import compose.components.buttons.SimpleButton
import compose.models.AddGameViewModel

@Composable
fun NavigationBar(
    navController: NavHostController,
    addGameViewModel: AddGameViewModel
) {
    var currentRoute by remember { mutableStateOf("all") }
    fun updateRoute(route: String) {
        currentRoute = route
        navController.navigate(currentRoute)
    }

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
                SimpleButton(weight40,"Deals") {
                    updateRoute("deals")
                }

                NavBarAddButton(currentRoute, {
                    updateRoute("add")
                }, {
                    addGameViewModel.addGame()
                    updateRoute("all")
                })

                SimpleButton(weight40, "All") {
                    updateRoute("all")
                }
            }
        }
    }
}