package core.services.games

import core.services.games.dto.GameDto
import core.utils.DatabaseUtils
import java.sql.ResultSet

class GamesService {
    private val databaseUtils = DatabaseUtils()

    fun getAllGames(): MutableList<GameDto> {
        val path = "/sql/games/get_all.sql"
        val rs = databaseUtils.executeQuery(path)
        require(rs != null)
        return databaseUtils.resultSetMapper(rs, ::resultSetToGameDto)
    }

    private fun resultSetToGameDto(rs: ResultSet): GameDto {
        return GameDto(
            id=rs.getLong("id"),
            name=rs.getString("name"),
            steamId=rs.getString("steam_id"),
            targetPrice=rs.getDouble("target_price"),
            targetDiscount=rs.getDouble("target_discount"),
        )
    }
}