package core.services.games

import core.services.games.dto.GameDto
import core.utils.DatabaseUtils
import org.koin.java.KoinJavaComponent
import java.sql.ResultSet

class GamesService {
    private fun databaseUtils(): DatabaseUtils {
        return KoinJavaComponent
            .getKoin()
            .get<DatabaseUtils>()
    }

    fun getAllGames(): List<GameDto> {
        val path = "/sql/games/get_all.sql"
        return databaseUtils().query(path, ::resultSetToGameDto)
    }

    private fun resultSetToGameDto(rs: ResultSet): GameDto {
        println(rs)
        return GameDto(
            id=rs.getLong("id"),
            name=rs.getString("name"),
            steamId=rs.getString("steam_id"),
            targetPrice=rs.getDouble("target_price"),
            targetDiscount=rs.getDouble("target_discount"),
        )
    }
}