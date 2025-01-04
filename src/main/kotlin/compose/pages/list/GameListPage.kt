package compose.pages.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import compose.models.DeleteGameViewModel
import compose.models.UpdateGameListViewModel
import core.services.DisplayGameDto
import kotlinx.coroutines.launch

@Composable
fun GameListPage (
    gameListPageType: GameListPageType,
    navHostController: NavHostController
) {
    val deleteGameViewModel = DeleteGameViewModel()
    val updateGameListViewModel = UpdateGameListViewModel()

    var listOfGames by remember { mutableStateOf(listOf<DisplayGameDto>()) }

    rememberCoroutineScope().launch {
        listOfGames = updateGameListViewModel.getAll(gameListPageType)
    }

    fun delete(id: Long) {
        deleteGameViewModel.deleteGame(id)
        when (gameListPageType) {
            GameListPageType.ALL -> navHostController.navigate("all")
            GameListPageType.DEALS -> navHostController.navigate("deals")
        }
    }

    MaterialTheme {
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            itemsIndexed(listOfGames) { _, item ->
                GameListEntry(
                    item,
                    ::delete,
                )
            }
        }
        if (listOfGames.isEmpty()) {
            Box(
                modifier = Modifier.padding(10.dp)
            ) {
                GameListEmptyCard()
            }
        }
    }
}