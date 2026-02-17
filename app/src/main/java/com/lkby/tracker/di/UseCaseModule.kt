package com.lkby.tracker.di

import com.lkby.tracker.domain.usecase.CreateTournamentUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<CreateTournamentUseCase> {
        CreateTournamentUseCase(get())
    }
}