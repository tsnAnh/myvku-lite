package dev.tsnanh.common.notification

import androidx.compose.ui.window.TrayState

actual class NotificationSenderPlatform(private val trayState: TrayState) : NotificationSender {
    actual override fun sendNotification(
        notification: Notification,
    ) {
        if (notification.title.isNotBlank() && notification.message.isNotBlank()) {
            trayState.sendNotification(notification.asDesktopNotification())
        }
    }
}

fun Notification.asDesktopNotification() = androidx.compose.ui.window.Notification(title, message)
