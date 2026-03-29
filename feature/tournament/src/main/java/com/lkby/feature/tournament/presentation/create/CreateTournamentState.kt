package com.lkby.feature.tournament.presentation.create

import com.lkby.feature.tournament.domain.model.TournamentFormat

data class CreateTournamentState(
    val name: String = "",
    val dateTime: Long? = null,
    val isTentative: Boolean = false,
    val format: TournamentFormat = TournamentFormat.SINGLE_ELIMINATION,
    val isValid: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)