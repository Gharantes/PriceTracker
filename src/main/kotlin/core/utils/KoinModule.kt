package core.utils

import core.services.games.GamesService
import core.utils.database.DatabaseUtils
import org.koin.dsl.module

val koinModule = module {
    single {
        DatabaseUtils()
    }
    single {
        GamesService()
    }
}