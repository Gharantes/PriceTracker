package core.utils

import core.services.api.steam.SteamApiService
import core.services.database.games.GamesService
import core.utils.database.DatabaseUtils
import org.koin.dsl.module

val koinModule = module {
    single {
        DatabaseUtils()
    }
    single {
        GamesService()
    }
    single {
        SteamApiService()
    }
}