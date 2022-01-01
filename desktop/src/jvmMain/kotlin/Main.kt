
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import dev.tsnanh.common.App
import dev.tsnanh.common.notification.NotificationSenderPlatform
import dev.tsnanh.common.theme.MyVKULiteTheme

fun main() = application {
    val trayState = rememberTrayState()
    Window(onCloseRequest = ::exitApplication, title = "My VKU Lite Desktop") {
        MyVKULiteTheme {
            App(notificationSender = NotificationSenderPlatform(trayState))
        }
    }
    Tray(
        icon = rememberVectorPainter(Icons.Default.Image),
        state = trayState,
        tooltip = "My VKU Lite"
    )
}
