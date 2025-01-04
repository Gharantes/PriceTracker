package utility

fun String.safeToDouble(): Double? {
    return try {
        this.replace(",", ".").toDouble()
    } catch (e: NumberFormatException) {
        null
    }
}