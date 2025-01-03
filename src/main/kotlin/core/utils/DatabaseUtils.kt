package core.utils

import java.io.File
import java.sql.*

private const val DATABASE_BASE_PATH = "game-price-checker.db"

class DatabaseUtils {
    private val resourceUtils = ResourceUtils()
    var initialized = false

    fun initialize() {
        require(dbExists()) { "Database could not be found." }
        createTable()
        initialized = true
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
        executeUpdate(path, true)
    }

    fun <T> query (
        path: String,
        mapper: (ResultSet) -> T,
    ): List<T> {
        if (!initialized) {
            return emptyList()
        }
        val connection = getDriveConnection()
        val statement = connection.createStatement()
        val sql = resourceUtils.getFileContentFromResources(path)
        val resultSet =  statement.executeQuery(sql)
        val results = resultSetMapper(resultSet, mapper)
        statement.close()
        connection.close()
        return results.toList()
    }
    fun <T> queryForObject(path: String, mapper: (ResultSet) -> T): T? {
        if (!initialized) {
            return null
        }
        val connection = getDriveConnection()
        val statement = connection.createStatement()
        val sql = resourceUtils.getFileContentFromResources(path)
        val resultSet =  statement.executeQuery(sql)
        val results = resultSetMapper(resultSet, mapper)
        statement.close()
        connection.close()
        return results.firstOrNull()
    }
    fun executeUpdate (
        path: String,
        skipValidation: Boolean = false
    ) {
        try {
            if (!skipValidation && !initialized) {
                return
            }
            val connection = getDriveConnection()
            val statement = connection.createStatement()
            val sql = resourceUtils.getFileContentFromResources(path)
            statement.executeUpdate(sql)
            statement.close()
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
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
        println(results)
        return results
    }
}