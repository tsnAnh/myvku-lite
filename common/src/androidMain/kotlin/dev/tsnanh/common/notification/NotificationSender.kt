package dev.tsnanh.common.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dev.tsnanh.common.R
import kotlin.random.Random

actual class NotificationSenderPlatform(private val context: Context) : NotificationSender {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManagerCompat.createNotificationChannel(
                NotificationChannel(
                    "myvkulite",
                    "My VKU Lite",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
    }

    actual override fun sendNotification(notification: Notification) {
        if (notification.title.isNotBlank() && notification.message.isNotBlank()) {
            val notificationShow = NotificationCompat.Builder(context, "myvkulite")
                .setAllowSystemGeneratedContextualActions(true)
                .setColorized(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(notification.message)
                .setContentTitle(notification.title)
                .setGroup("myvkulite")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build()
            val summary = NotificationCompat.Builder(context, "myvkulite")
                .setGroupSummary(true)
                .setGroup("myvkulite")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()

            notificationManagerCompat.notify(Random.nextInt(999), notificationShow)
            notificationManagerCompat.notify(9999, summary)
        }
    }
}
