package core.services.api.steam

import core.services.api.steam.dto.SteamAppDetailsEntry
import core.utils.RetrofitUtils
import retrofit2.awaitResponse

class SteamApiService {
    private val api = RetrofitUtils().steam()

    suspend fun getAppDeDetails(
        id: String
    ): SteamAppDetailsEntry? {
        return try {
            val response = api.getAppDetails(id).awaitResponse()
            response.body()?.get(id)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}