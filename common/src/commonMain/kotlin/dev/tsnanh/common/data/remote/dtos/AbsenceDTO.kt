package dev.tsnanh.common.data.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbsenceDTO(
    @SerialName("tenlop")
    val className: String,
    @SerialName("chucdanh")
    val title: String,
    @SerialName("ten")
    val firstName: String,
    @SerialName("hodem")
    val lastName: String,
    @SerialName("ngaybaonghi")
    val dateNotice: String
)
