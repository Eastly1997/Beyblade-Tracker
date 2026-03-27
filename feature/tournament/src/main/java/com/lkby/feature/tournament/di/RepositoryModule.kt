package com.lkby.feature.tournament.di

import com.lkby.feature.tournament.data.repository.TournamentRepositoryImpl
import com.lkby.feature.tournament.domain.repository.TournamentRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<TournamentRepository> {
        TournamentRepositoryImpl(get())
    }
}

