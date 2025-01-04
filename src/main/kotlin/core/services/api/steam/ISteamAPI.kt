package core.services.api.steam

import core.services.api.steam.dto.SteamAppDetailsEntry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ISteamAPI {
    @GET("appdetails/")
    fun getAppDetails(
        @Query("appids") appids: String
    ): Call<Map<String, SteamAppDetailsEntry>>
}