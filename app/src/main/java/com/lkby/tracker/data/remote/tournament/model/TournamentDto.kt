package com.lkby.tracker.data.remote.tournament.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TournamentDto(
    @SerialName("id")
    val id: String = "",

    @SerialName("name")
    val name: String = "",

    @SerialName("description")
    val description: String = "",

    @SerialName("organizer_id")
    val organizerId: String = "",

    @SerialName("type")
    val type: String = "",

    @SerialName("format")
    val format: String = "",

    @SerialName("location")
    val location: TournamentLocationDto = TournamentLocationDto(),

    @SerialName("entrance_fee")
    val entranceFee: EntranceFeeDto = EntranceFeeDto(),

    @SerialName("entrance_benefits")
    val entranceBenefits: List<EntranceBenefitDto> = emptyList(),

    @SerialName("pre_registration")
    val preRegistration: PreRegistrationDto? = null,

    @SerialName("prize_pool")
    val prizePool: PrizePoolDto = PrizePoolDto(),

    @SerialName("max_participants")
    val maxParticipants: Int = 0,

    @SerialName("status")
    val status: String = "",

    @SerialName("start_time")
    val startTime: Long = 0,

    @SerialName("created_at")
    val createdAt: Long = 0,

    @SerialName("updated_at")
    val updatedAt: Long = 0
)