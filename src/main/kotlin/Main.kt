import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import compose.App
import core.utils.DatabaseUtils


private const val WIDTH = 500
private const val HEIGHT = 720

fun main() = application {
    DatabaseUtils().initialize()
    Window(
        onCloseRequest = ::exitApplication,
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
