package core.services

data class DisplayGameDto(
    val id: Long,
    val name: String,
    val steamId: String?,
    val targetPrice: Double?,
    val targetDiscount: Double?,
    var steamInfo: DisplayGameSteamInfoDto? = null
) {
    fun steamMeetsTargetPrice(): Boolean {
        if (steamInfo == null) { return false }
        val target = targetPrice ?: return false
        val steam = steamInfo?.final ?: return false
        return (steam/100) <= target
    }
    fun steamMeetsTargetDiscount(): Boolean {
        if (steamInfo == null) { return false }
        val target = targetDiscount ?: return false
        val steam = steamInfo?.discountPercent ?: return false
        return steam >= target
    }
}

data class DisplayGameSteamInfoDto(
    var currency: String?,
    var initial: Double?,
    var final: Double?,
    var discountPercent: Double?,
    var initialFormatted: String?,
    var finalFormatted: String?,
    var totalAchievements: Int?,
    var headerImage: String?,
    var capsuleImage: String?,
    var capsuleImageV5: String?,
    var isComingSoon: Boolean?,
    var releaseDate: String?,
)