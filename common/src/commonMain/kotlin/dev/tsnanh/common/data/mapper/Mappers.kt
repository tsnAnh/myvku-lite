package dev.tsnanh.common.data.mapper

import dev.tsnanh.common.data.remote.dtos.AbsenceDTO
import dev.tsnanh.common.models.Absence

fun List<AbsenceDTO>.mapToDomainAbsences() = map {
    Absence(
        className = it.className,
        title = it.title,
        firstName = it.firstName,
        it.lastName,
        dateNotice = it.dateNotice
    )
}
