package compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import compose.components.navigationBar.NavigationBar
import compose.models.AddGameViewModel
import compose.pages.add.AddGamePage
import compose.pages.list.GameListPage

@Composable
fun App() {
    val navController = rememberNavController()

    val addGameViewModel = AddGameViewModel()

    MaterialTheme {
        Column {
            NavHost(navController, startDestination = "all") {
                composable("deals") {
                    GameListPage()
                }
                composable("add") {
                    AddGamePage(addGameViewModel)
                }
                composable("all") {
                    GameListPage()
                }
            }
            Spacer(Modifier.weight(1f, true))
            NavigationBar(
                navController,
                addGameViewModel
            )
        }
    }
}