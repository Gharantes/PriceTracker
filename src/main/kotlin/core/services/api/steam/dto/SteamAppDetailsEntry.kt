package core.services.api.steam.dto

import com.google.gson.annotations.SerializedName

data class SteamAppDetailsEntry(
    val success: Boolean,
    val data: SteamAppDetailsData
)