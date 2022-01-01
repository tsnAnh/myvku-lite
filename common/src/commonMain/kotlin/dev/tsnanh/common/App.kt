package dev.tsnanh.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.tsnanh.common.notification.NotificationSender
import dev.tsnanh.common.ui.AbsenceScreen

val LocalAppState = compositionLocalOf<AppState> { error("No AppState provided!") }

@Composable
fun App(notificationSender: NotificationSender) {
    val appState = rememberAppState(notificationSender)
    val viewModel: MyVKULiteViewModel = MyVKULiteViewModelImpl()
    CompositionLocalProvider(LocalAppState providesDefault appState) {
        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    AppBottomNav(appState)
                }
            }
        ) {
            Row {
                if (!appState.shouldShowBottomBar) {
                    AppNavRail(appState)
                }
                when (appState.currentDestination.value) {
                    Destination.News -> AbsenceScreen(viewModel)
                    Destination.Timetable -> Box(
                        modifier = Modifier.fillMaxSize().background(Color.Cyan)
                    )
                }
            }
        }
    }
}

@Composable
private fun AppBottomNav(appState: AppState) {
    BottomNavigation {
        listOf(Destination.News, Destination.Timetable).forEach {
            BottomNavigationItem(
                selected = appState.isCurrentDestination(it),
                onClick = {
                    appState.updateDestination(it)
                },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                label = { Text(text = it.title) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondaryVariant
            )
        }
    }
}

@Composable
private fun AppNavRail(appState: AppState) {
    NavigationRail {
        listOf(Destination.News, Destination.Timetable).forEach {
            NavigationRailItem(
                selected = appState.isCurrentDestination(it),
                onClick = {
                    appState.updateDestination(it)
                },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                label = { Text(text = it.title) },
            )
        }
    }
}
