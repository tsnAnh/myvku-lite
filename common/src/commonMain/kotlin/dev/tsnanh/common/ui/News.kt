package dev.tsnanh.common.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.tsnanh.common.LocalAppState
import dev.tsnanh.common.MyVKULiteViewModel

@Composable
fun AbsenceScreen(viewModel: MyVKULiteViewModel) {
    val absences by viewModel.absences.collectAsState()
    val appState = LocalAppState.current
    LazyColumn {
        items(absences) {
            TextButton(onClick = { appState.notificationSender.sendNotification {
                title = it.className
                message = it.lastName.plus(" ${it.firstName}")
            } }) {
                Text(text = it.className)
            }
        }
    }
}
