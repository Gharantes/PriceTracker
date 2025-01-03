package compose.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import core.services.games.GamesService
import core.services.games.dto.InsertGameDto
import org.koin.java.KoinJavaComponent
import utility.toDouble2

class AddGameViewModel : ViewModel() {
    var name by mutableStateOf("")
    var targetPrice by mutableStateOf("")
    var targetDiscount by mutableStateOf("")
    var steamId by mutableStateOf("")

    fun addGame() {
        if (validateFields()) {
            getGamesService().addGame(
                InsertGameDto(
                    name = name,
                    steamId = steamId,
                    targetPrice = targetPrice.toDouble2(),
                    targetDiscount = targetDiscount.toDouble2()
                )
            )
        }
    }

    fun validateFields(): Boolean {
        return listOf(
            validateName(),
            validateTargetPrice(),
        ).contains(false)
    }
    private fun validateName(): Boolean {
        return name.isNotBlank()
    }
    private fun validateTargetPrice(): Boolean {
        val a = targetPrice.isNotBlank()
        val b = targetPrice.toDouble2() != null
        return a && b
    }


    private fun getGamesService(): GamesService {
        return KoinJavaComponent
            .getKoin()
            .get<GamesService>()
    }
}