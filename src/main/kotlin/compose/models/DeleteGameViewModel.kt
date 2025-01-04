package compose.models

import androidx.lifecycle.ViewModel
import core.services.database.games.GamesService
import org.koin.java.KoinJavaComponent

class DeleteGameViewModel : ViewModel () {
    fun deleteGame(id: Long) {
        getGamesService().deleteGame(id)
    }
    private fun getGamesService(): GamesService {
        return KoinJavaComponent
            .getKoin()
            .get<GamesService>()
    }
}