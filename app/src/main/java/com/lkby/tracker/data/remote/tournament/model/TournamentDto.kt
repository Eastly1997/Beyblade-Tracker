package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TournamentDto(
    @SerialName("id") val id: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("created_by") val createdBy: String = "",
    @SerialName("max_participants") val maxParticipants: Int = 0,
    @SerialName("created_at") val createdAt: Long = 0
)