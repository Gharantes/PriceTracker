package compose.models

import androidx.lifecycle.ViewModel
import compose.pages.list.GameListPageType
import core.services.DisplayGameDto
import core.services.DisplayGameSteamInfoDto
import core.services.api.steam.SteamApiService
import core.services.database.games.GamesService
import core.services.database.games.dto.GameDto
import org.koin.java.KoinJavaComponent

class UpdateGameListViewModel : ViewModel() {
    suspend fun getAll(
        gameListPageType: GameListPageType
    ): List<DisplayGameDto> {
        val steamApiService = getSteamApiService()
        val list = getGamesService().getAllGames().map {
            val initial = toDisplayGameDto(it)
            getSteamInfo(steamApiService, initial)
            initial
        }
        println(list)
        return when (gameListPageType) {
            GameListPageType.ALL -> list
            GameListPageType.DEALS -> list.filter { item ->
                item.steamMeetsTargetPrice() || item.steamMeetsTargetDiscount()
            }
        }
    }

    private fun toDisplayGameDto(
        game: GameDto,
    ): DisplayGameDto {
        return DisplayGameDto(
            id = game.id,
            name = game.name,
            steamId = game.steamId,
            targetPrice = game.targetPrice,
            targetDiscount = game.targetDiscount,
        )
    }
    private suspend fun getSteamInfo(
        steamApiService: SteamApiService,
        initial: DisplayGameDto
    ) {
        initial.apply {
            if (steamId != null) {
                val temp = steamApiService.getAppDeDetails(steamId)
                steamInfo = DisplayGameSteamInfoDto(
                    // Price Info
                    discountPercent=temp?.data?.priceOverview?.discountPercent,
                    currency=temp?.data?.priceOverview?.currency,
                    // Value in Cents
                    initial=temp?.data?.priceOverview?.initial,
                    final=temp?.data?.priceOverview?.final,
                    // Formatted
                    initialFormatted=temp?.data?.priceOverview?.initialFormatted,
                    finalFormatted=temp?.data?.priceOverview?.finalFormatted,
                    totalAchievements=temp?.data?.achievements?.total,
                    // Images
                    headerImage=temp?.data?.headerImage,
                    capsuleImage=temp?.data?.capsuleImage,
                    capsuleImageV5=temp?.data?.capsuleImageV5,
                    // Release Info
                    isComingSoon=temp?.data?.releaseDate?.comingSoon,
                    releaseDate=temp?.data?.releaseDate?.date,
                )
            }
        }
    }
    private fun getGamesService(): GamesService {
        return KoinJavaComponent
            .getKoin()
            .get<GamesService>()
    }
    private fun getSteamApiService(): SteamApiService {
        return KoinJavaComponent
            .getKoin()
            .get<SteamApiService>()
    }
}