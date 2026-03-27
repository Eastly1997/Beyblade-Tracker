package com.lkby.feature.tournament.di

import com.lkby.feature.tournament.domain.usecase.CreateTournamentUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CreateTournamentUseCase(get(), get()) }
}