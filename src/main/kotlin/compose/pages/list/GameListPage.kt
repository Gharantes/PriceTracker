package compose.pages.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.services.games.GamesService
import core.services.games.dto.GameDto
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

@Composable
fun GameListPage () {
    var listOfGames by remember {
        mutableStateOf<List<GameDto>>(
            emptyList()
        )
    }

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        listOfGames = updateGameList()
    }
    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ){
        itemsIndexed(listOfGames) { _, item ->
            GameListEntry(item)
        }
    }
}

fun updateGameList(): List<GameDto> {
    return KoinJavaComponent
        .getKoin()
        .get<GamesService>()
        .getAllGames()
}