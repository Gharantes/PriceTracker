package core.utils

import core.services.api.steam.ISteamAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtils {
    private fun <T> base(
        url: String,
        iClass: Class<T>
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(iClass)
    }
    fun steam(): ISteamAPI {
        return base(
            url = "http://store.steampowered.com/api/",
            iClass = ISteamAPI::class.java
        )
    }
}