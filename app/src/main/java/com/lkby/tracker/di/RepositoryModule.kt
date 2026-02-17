package com.lkby.tracker.di

import com.lkby.tracker.data.repository.AuthRepositoryImpl
import com.lkby.tracker.data.repository.TournamentRepositoryImpl
import com.lkby.tracker.domain.repository.AuthRepository
import com.lkby.tracker.domain.repository.TournamentRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TournamentRepository> {
        TournamentRepositoryImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}

