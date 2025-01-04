package core.services.database.games

import core.services.database.games.dto.GameDto
import core.services.database.games.dto.InsertGameDto
import core.utils.database.DatabaseStringType
import core.utils.database.DatabaseUtils
import org.koin.java.KoinJavaComponent
import utility.nullableGetDouble
import java.sql.ResultSet

class GamesService {
    private val databaseUtils = getDatabaseUtils()
    private fun getDatabaseUtils(): DatabaseUtils {
        return KoinJavaComponent
            .getKoin()
            .get<DatabaseUtils>()
    }

    fun addGame(gameDto: InsertGameDto) {
        val sql = """
            INSERT INTO GAMES (name, steam_id, target_price, target_discount) 
            VALUES (
            '${gameDto.name}', 
            ${optionalStringParameter(gameDto.steamId)},
            ${optionalNumericParameter(gameDto.targetPrice)},
            ${optionalNumericParameter(gameDto.targetDiscount)}
        )
        """.trimIndent()
        return databaseUtils.update(sql, DatabaseStringType.QUERY)
    }
    private fun optionalStringParameter(param: Any?): String {
        return if (param == null || (param is String && param.isBlank())) {
            "null"
        } else {
            "'$param'"
        }
    }
    private fun optionalNumericParameter(param: Double?): String {
        return param?.toString() ?: "null"
    }


    fun getAllGames(): List<GameDto> {
        val path = "/sql/games/get_all.sql"
        return databaseUtils.query(path, ::resultSetToGameDto)
    }
    fun deleteGame(id: Long) {
        val query = "DELETE FROM GAMES WHERE id = $id"
        return databaseUtils.update(query, DatabaseStringType.QUERY)
    }
    private fun resultSetToGameDto(rs: ResultSet): GameDto {
        println(rs)
        return GameDto(
            id=rs.getLong("id"),
            name=rs.getString("name"),
            steamId=rs.getString("steam_id"),
            targetPrice=rs.nullableGetDouble("target_price"),
            targetDiscount=rs.nullableGetDouble("target_discount"),
        )
    }
}