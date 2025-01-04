package core.services.api.steam.dto

import com.google.gson.annotations.SerializedName

data class SteamAppDetailsReleaseDate(
    @SerializedName("coming_soon")
    val comingSoon: Boolean,
    val date: String
)
