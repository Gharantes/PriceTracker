package core.services.api.steam.dto

import com.google.gson.annotations.SerializedName

data class SteamAppDetailsPriceOverview(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("initial")
    val initial: Double,
    @SerializedName("final")
    val final: Double,
    @SerializedName("discount_percent")
    val discountPercent: Double,
    @SerializedName("initial_formatted")
    val initialFormatted: String,
    @SerializedName("final_formatted")
    val finalFormatted: String,
)
