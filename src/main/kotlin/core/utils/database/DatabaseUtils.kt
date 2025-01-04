package core.utils.database

import core.utils.ResourceUtils
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class DatabaseUtils {
    private val resourceUtils = ResourceUtils()
    private var initialized = false

    fun initialize() {
        require(dbExists()) { "Database could not be found." }
        createTable()
        initialized = true
    }
    private fun dbExists(): Boolean {
        val directory = System.getProperty("user.dir")
        val dbPath = File(directory, DATABASE_PATH)
        val dbParent = dbPath.parentFile
        if (!dbParent.exists()) {
            dbParent.mkdirs()
        }
        return dbPath.exists()
    }
    private fun createTable() {
        val path = "/sql/db/create-table-games.sql"
        update(path)
    }
    fun <T> query (
        str: String,
        mapper: (ResultSet) -> T,
        databaseStringType: DatabaseStringType = DatabaseStringType.PATH
    ): List<T> {
        val sql = getSql(str, databaseStringType)
        return execute(sql) { statement ->
            val resultSet = statement.executeQuery()
            resultSetMapper(resultSet, mapper)
        }
    }
    fun <T> queryForObject (
        str: String,
        mapper: (ResultSet) -> T,
        databaseStringType: DatabaseStringType = DatabaseStringType.PATH
    ): T? {
        val sql = getSql(str, databaseStringType)
        return execute(sql) { statement ->
            val resultSet = statement.executeQuery()
            resultSetMapper(resultSet, mapper).firstOrNull()
        }
    }
    fun update (
        str: String,
        databaseStringType: DatabaseStringType = DatabaseStringType.PATH
    ) {
        val sql = getSql(str, databaseStringType)
        execute(sql) { it.executeUpdate() }
    }

    private fun <T> execute(
        sql: String,
        fn: (PreparedStatement) -> T
    ): T {
        getDriveConnection().use { connection ->
            connection.prepareStatement(sql).use { statement ->
                return fn(statement)
            }
        }
    }
    private fun getSql(
        str: String,
        databaseStringType: DatabaseStringType = DatabaseStringType.PATH
    ): String {
        return when (databaseStringType) {
            DatabaseStringType.QUERY -> str
            DatabaseStringType.PATH -> resourceUtils.getFileContentFromResources(str)
        }
    }

    private fun getDriveConnection(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:$DATABASE_PATH")
    }

    private fun <T> resultSetMapper(
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