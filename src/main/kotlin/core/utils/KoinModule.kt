package core.utils

import org.koin.dsl.module

val koinModule = module {
    single {
        DatabaseUtils()
    }
}