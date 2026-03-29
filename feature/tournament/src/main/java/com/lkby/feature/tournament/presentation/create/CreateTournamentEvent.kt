package com.lkby.feature.tournament.presentation.create

import com.lkby.feature.tournament.domain.model.TournamentFormat

sealed class CreateTournamentEvent {
    data class NameChanged(val value: String) : CreateTournamentEvent()
    data class DateTimeSelected(val value: Long) : CreateTournamentEvent()
    data class FormatChanged(val format: TournamentFormat) : CreateTournamentEvent()
    data class TentativeChanged(val value: Boolean) : CreateTournamentEvent()
    object Create : CreateTournamentEvent()
}