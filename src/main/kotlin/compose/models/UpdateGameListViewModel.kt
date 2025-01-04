package compose.models

import androidx.lifecycle.ViewModel
import core.services.games.GamesService
import core.services.games.dto.GameDto
import org.koin.java.KoinJavaComponent

class UpdateGameListViewModel : ViewModel() {
    var listOfGames = listOf<GameDto>()

    fun getAll() {
        listOfGames = getGamesService().getAllGames()
    }

    fun getOnlyDeals() {
        val list = getGamesService().getAllGames()

        list.map {

        }
    }
    private fun getGamesService(): GamesService {
        return KoinJavaComponent
            .getKoin()
            .get<GamesService>()
    }
}