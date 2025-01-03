package utility

fun String.toDouble2(): Double? {
    return try {
        this.replace(",", ".").toDouble()
    } catch (e: NumberFormatException) {
        null
    }
}