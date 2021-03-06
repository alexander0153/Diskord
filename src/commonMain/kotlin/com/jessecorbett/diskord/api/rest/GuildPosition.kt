package com.jessecorbett.diskord.api.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildPosition(
    @SerialName("id") val id: String,
    @SerialName("position") val position: Int
)
