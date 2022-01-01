package dev.tsnanh.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarViewMonth
import androidx.compose.material.icons.rounded.TableRows
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(val icon: ImageVector, val title: String) {
    object News : Destination(icon = Icons.Rounded.CalendarViewMonth, title = "News")
    object Timetable : Destination(icon = Icons.Rounded.TableRows, title = "Timetable")
}
