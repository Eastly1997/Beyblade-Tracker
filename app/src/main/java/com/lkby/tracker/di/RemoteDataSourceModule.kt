package com.lkby.tracker.di

import com.lkby.tracker.data.remote.tournament.FirebaseTournamentDataSource
import com.lkby.tracker.data.remote.tournament.TournamentDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<TournamentDataSource> {
        FirebaseTournamentDataSource(
            firestore = get()
        )
    }
}