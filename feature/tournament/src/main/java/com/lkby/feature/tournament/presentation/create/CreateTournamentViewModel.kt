package com.lkby.feature.tournament.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkby.feature.tournament.domain.usecase.CreateTournamentUseCase
import com.lkby.feature.tournament.domain.usecase.model.CreateTournamentParams
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateTournamentViewModel(
    private val createTournamentUseCase: CreateTournamentUseCase,
    private val userId: String
) : ViewModel() {

    private val _state = MutableStateFlow(CreateTournamentState())
    val state: StateFlow<CreateTournamentState> = _state

    private val _navigation = MutableSharedFlow<CreateTournamentNavigation>()
    val navigation: SharedFlow<CreateTournamentNavigation> = _navigation

    fun onEvent(event: CreateTournamentEvent) {

        when (event) {
            is CreateTournamentEvent.NameChanged -> {
                updateState { copy(name = event.value) }
            }

            is CreateTournamentEvent.DateTimeSelected -> {
                updateState { copy(dateTime = event.value) }
            }

            is CreateTournamentEvent.FormatChanged -> {
                updateState { copy(format = event.format) }
            }

            is CreateTournamentEvent.TentativeChanged -> {
                updateState {
                    copy(
                        isTentative = event.value
                    )
                }
            }

            CreateTournamentEvent.Create -> {
                createTournament()
            }
        }

        validate()
    }

    private fun createTournament() = viewModelScope.launch {

        val currentState = _state.value

        _state.update { it.copy(isLoading = true) }

        val params = CreateTournamentParams(
            name = currentState.name,
            organizerId = userId,
            startTime = currentState.dateTime,
            format = currentState.format
        )

        createTournamentUseCase(params)
            .onSuccess { tournament ->
                _state.update {
                    it.copy(isLoading = false)
                }
                _navigation.emit(
                    CreateTournamentNavigation.Success(tournament.id)
                )
            }
            .onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
    }

    private fun validate() {
        val currentState = _state.value
        val isValid = currentState.name.isNotBlank() &&
                (currentState.isTentative || currentState.dateTime != null)

        _state.update {
            it.copy(isValid = isValid)
        }
    }

    private fun updateState(
        reducer: CreateTournamentState.() -> CreateTournamentState
    ) {
        _state.update { it.reducer() }
    }
}