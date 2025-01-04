package compose.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import core.services.games.GamesService
import core.services.games.dto.InsertGameDto
import org.koin.java.KoinJavaComponent
import utility.safeToDouble

class AddGameViewModel : ViewModel() {
    var name by mutableStateOf("")
    var targetPrice by mutableStateOf("")
    var targetDiscount by mutableStateOf("")
    var steamId by mutableStateOf("")

    fun addGame() {
        if (name.isBlank()) {
            return
        }

        getGamesService().addGame(
            InsertGameDto(
                name = name,
                steamId = steamId,
                targetPrice = targetPrice.safeToDouble(),
                targetDiscount = targetDiscount.safeToDouble()
            )
        )
    }

    private fun getGamesService(): GamesService {
        return KoinJavaComponent
            .getKoin()
            .get<GamesService>()
    }
}