package compose.pages.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.services.games.dto.GameDto

@Composable
fun GameListPage () {
    val listOfGames by remember {
        mutableStateOf<List<GameDto>>(
            listOf(
                GameDto(
                    id = 1,
                    name = "TEST",
                    targetDiscount = 60.0,
                    targetPrice = 100.0,
                    steamId = "1213"
                ),
                GameDto(
                    id = 2,
                    name = "TEST2",
                    targetDiscount = 25.0,
                    targetPrice = 20.0,
                    steamId = "1213"
                )
            )
        )
    }

    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ){
        itemsIndexed(listOfGames) { _, item ->
            GameListEntry(item)
        }
    }
}