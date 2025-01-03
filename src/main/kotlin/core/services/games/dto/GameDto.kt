package core.services.games.dto

data class GameDto(
    val id: Long,
    val name: String,
    val steamId: String?,
    val targetPrice: Double?,
    val targetDiscount: Double?
)
