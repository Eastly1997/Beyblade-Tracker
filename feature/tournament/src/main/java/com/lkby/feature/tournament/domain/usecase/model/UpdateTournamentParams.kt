package com.lkby.feature.tournament.domain.usecase.model

import com.lkby.feature.tournament.domain.model.EntranceBenefit
import com.lkby.feature.tournament.domain.model.EntranceFee
import com.lkby.feature.tournament.domain.model.PreRegistration
import com.lkby.feature.tournament.domain.model.PrizePool
import com.lkby.feature.tournament.domain.model.TournamentLocation

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