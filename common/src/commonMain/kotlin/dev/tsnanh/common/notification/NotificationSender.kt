package dev.tsnanh.common.notification

interface NotificationSender {
    fun sendNotification(notification: Notification)
    fun sendNotification(build: Notification.() -> Unit) {
        val notificationBuilder = Notification("", "")
        sendNotification(notificationBuilder.apply(build))
    }
}

expect class NotificationSenderPlatform : NotificationSender {
    override fun sendNotification(notification: Notification)
}

data class Notification(var title: String, var message: String)
