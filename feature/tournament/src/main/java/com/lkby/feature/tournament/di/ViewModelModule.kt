package com.lkby.feature.tournament.di

import com.lkby.feature.tournament.presentation.create.CreateTournamentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (userId: String) ->
        CreateTournamentViewModel(
            createTournamentUseCase = get(),
            userId = userId
        )
    }
}