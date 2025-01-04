package compose.pages.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import compose.models.DeleteGameViewModel
import compose.models.UpdateGameListViewModel

@Composable
fun GameListPage (
    gameListPageType: GameListPageType,
    navHostController: NavHostController
) {
    val deleteGameViewModel = DeleteGameViewModel()
    val updateGameListViewModel = UpdateGameListViewModel()

    fun delete(id: Long) {
        deleteGameViewModel.deleteGame(id)
        when (gameListPageType) {
            GameListPageType.ALL -> navHostController.navigate("all")
            GameListPageType.DEALS -> navHostController.navigate("deals")
        }
    }

    updateGameListViewModel.getAll()

    MaterialTheme {
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            itemsIndexed(updateGameListViewModel.listOfGames) { _, item ->
                GameListEntry(
                    item,
                    ::delete,
                )
            }
        }
    }
}