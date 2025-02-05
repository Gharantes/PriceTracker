import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import compose.App
import core.utils.database.DatabaseUtils
import core.utils.koinModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent


private const val WIDTH = 500
private const val HEIGHT = 720

fun main() = application {
    startKoin() {
        modules(koinModule)
    }
    initializeDb()

    Window(
        onCloseRequest = {
            stopKoin()
            exitApplication()
        },
        state = rememberWindowState(
            size = DpSize(
                width = WIDTH.dp,
                height = HEIGHT.dp
            )
        )
    ) {
        App()
    }
}

private fun initializeDb() {
    KoinJavaComponent
        .getKoin()
        .get<DatabaseUtils>()
        .initialize()
}
