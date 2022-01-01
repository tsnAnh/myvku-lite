package dev.tsnanh.common

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.tsnanh.common.notification.NotificationSender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppState(
    val notificationSender: NotificationSender,
    val scaffoldState: ScaffoldState,
) {
    var currentDestination = mutableStateOf<Destination>(Destination.News)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val shouldShowBottomBar: Boolean
        get() = getPlatformName() == "Android"

    fun isCurrentDestination(destinations: Destination) = destinations == currentDestination.value

    fun updateDestination(destinations: Destination) {
        currentDestination.value = destinations
    }

    fun showSnackbar(message: String) = coroutineScope.launch {
        scaffoldState.snackbarHostState.showSnackbar(message)
    }
}

@Composable
fun rememberAppState(notificationSender: NotificationSender, scaffoldState: ScaffoldState = rememberScaffoldState()) =
    remember(notificationSender, scaffoldState) { AppState(notificationSender, scaffoldState) }
