package dev.tsnanh.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.tsnanh.common.App
import dev.tsnanh.common.notification.NotificationSenderPlatform
import dev.tsnanh.common.theme.MyVKULiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyVKULiteTheme {
                App(NotificationSenderPlatform(context = this))
            }
        }
    }
}
