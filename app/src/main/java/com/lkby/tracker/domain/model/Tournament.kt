package com.lkby.tracker.domain.model

data class Tournament(
    val id: String,
    val name: String,
    val description: String,
    val organizerId: String,
    val type: TournamentType,
    val format: TournamentFormat,
    val location: TournamentLocation,
    val entranceFee: EntranceFee,
    val entranceBenefits: List<EntranceBenefit>,
    val preRegistration: PreRegistration?,
    val prizePool: PrizePool,
    val maxParticipants: Int,
    val status: TournamentStatus,
    val startTime: Long,
    val createdAt: Long,
    val updatedAt: Long
)

enum class TournamentType {
    OFFICIAL,
    UNOFFICIAL
}

enum class TournamentStatus {
    DRAFT,
    PRE_REGISTRATION,
    REGISTRATION,
    CHECK_IN,
    IN_PROGRESS,
    COMPLETED,
    ARCHIVED
}

enum class TournamentFormat {
    SINGLE_ELIMINATION
}