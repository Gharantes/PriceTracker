package core.services.database.games.dto

data class InsertGameDto (
    val name: String,
    val steamId: String?,
    val targetPrice: Double?,
    val targetDiscount: Double?
)