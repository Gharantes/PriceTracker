package core.utils

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
        val path = "/sql/db/create_table_games.sql"
        execute(path, ::executeUpdate)
    }


    fun <T> execute (
        path: String,
        executorType: (Statement, String) -> T
    ): T? {
        return try {
            val connection = getDriveConnection()
            val statement = connection.createStatement()
            val result = executorType(
                statement,
                resourceUtils.getFileContentFromResources(path)
            )
            statement.close()
            connection.close()
            result
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun executeUpdate(
        statement: Statement,
        sql: String
    ) {
        statement.executeUpdate(sql)
    }
    fun executeQuery(
        statement: Statement,
        sql: String
    ): ResultSet {
        return statement.executeQuery(sql)
    }
    private fun getDriveConnection(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:$DATABASE_BASE_PATH")
    }
}