package core.utils

import java.io.File

class ResourceUtils {
    fun getFileFromResources(path: String): File {
        val result = ResourceUtils::class.java.getResource(path)
        require(result != null) {
            "File not found: $path"
        }
        return File(result.path)
    }
    fun getFileContentFromResources(path: String): String {
        val result = ResourceUtils::class.java.getResource(path)
        require(result != null) {
            "File not found: $path"
        }
        return result.readText()
    }
}