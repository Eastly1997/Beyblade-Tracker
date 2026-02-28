package com.lkby.tracker.domain.usecase.model

import com.lkby.tracker.domain.model.EntranceBenefit
import com.lkby.tracker.domain.model.EntranceFee
import com.lkby.tracker.domain.model.PreRegistration
import com.lkby.tracker.domain.model.PrizePool
import com.lkby.tracker.domain.model.TournamentLocation

data class UpdateTournamentParams(
    val tournamentId: String,
    val name: String,
    val description: String,
    val location: TournamentLocation,
    val entranceFee: EntranceFee,
    val entranceBenefits: List<EntranceBenefit>,
    val preRegistration: PreRegistration?,
    val prizePool: PrizePool,
    val maxParticipants: Int,
    val startTime: Long
)