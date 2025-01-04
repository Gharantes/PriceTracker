package utility

import java.sql.ResultSet

fun ResultSet.nullableGetDouble(colname: String): Double? {
    val result = this.getDouble(colname)
    return if (this.wasNull()) null else result
}