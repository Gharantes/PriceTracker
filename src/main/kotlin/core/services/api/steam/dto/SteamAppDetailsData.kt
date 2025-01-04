package core.services.api.steam.dto

import com.google.gson.annotations.SerializedName
import core.services.api.steam.dto.SteamAppDetailsReleaseDate

data class SteamAppDetailsData(
    val name: String,
    val dlc: List<Long>?, // ID
    @SerializedName("header_image")
    val headerImage: String?,
    @SerializedName("capsule_image")
    val capsuleImage: String?,
    @SerializedName("capsule_imagev5")
    val capsuleImageV5: String?,
    @SerializedName("price_overview")
    val priceOverview: SteamAppDetailsPriceOverview,
    @SerializedName("achievements")
    val achievements: SteamAppDetailsAchievements,
    @SerializedName("release_date")
    val releaseDate: SteamAppDetailsReleaseDate
)
