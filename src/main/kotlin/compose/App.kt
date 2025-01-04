package compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        Surface(
            color = Color(0xFF121212),
            elevation = 0.dp
        ) {
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
}