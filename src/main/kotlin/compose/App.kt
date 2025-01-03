package compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import compose.components.navigationBar.NavigationBar
import compose.pages.add.AddGamePage
import compose.pages.list.ListGamePage

@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        Column {
            NavHost(navController, startDestination = "all") {
                composable("deals") {
                    ListGamePage()
                }
                composable("add") {
                    AddGamePage()
                }
                composable("all") {
                    ListGamePage()
                }
            }
            NavigationBar(navController)
        }
    }
}