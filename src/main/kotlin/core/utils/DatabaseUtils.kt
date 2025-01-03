package core.utils

import core.services.games.dto.GameDto
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

private const val DATABASE_BASE_PATH = "game-price-checker.db"

class DatabaseUtils {
    private val resourceUtils = ResourceUtils()

    fun initialize() {
        println("Was executed")
//        require(dbExists()) { "Database could not be found." }
//        createTable()
    }
    private fun dbExists(): Boolean {
        val directory = System.getProperty("user.dir")
        val dbPath = File(directory, DATABASE_BASE_PATH)
        val dbParent = dbPath.parentFile
        if (!dbParent.exists()) {
            dbParent.mkdirs()
        }
        return dbPath.exists()
    }
    private fun createTable() {
        val path = "/sql/db/create-table-games.sql"
        executeUpdate(path)
    }

    fun executeQuery (
        path: String
    ): ResultSet? {
        return execute(path) { st, sql ->
            st.executeQuery(sql)
        }
    }
    fun executeUpdate (
        path: String
    ): Int? {
        return execute(path) { st, sql ->
            st.executeUpdate(sql)
        }
    }
    private fun <T> execute(
        path: String,
        statementFn: (Statement, String) -> T?
    ): T? {
        return try {
            val connection = getDriveConnection()
            val statement = connection.createStatement()
            val sql = resourceUtils.getFileContentFromResources(path)
            statement.close()
            connection.close()
            statementFn(statement, sql)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    private fun getDriveConnection(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:$DATABASE_BASE_PATH")
    }
    fun <T> resultSetMapper(
        rs: ResultSet,
        mapper: (ResultSet) -> T
    ): MutableList<T> {
        val results = mutableListOf<T>()
        while (rs.next()) {
            results.add(mapper(rs))
        }
        return results
    }
}