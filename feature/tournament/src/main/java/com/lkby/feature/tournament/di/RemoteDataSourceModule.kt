package com.lkby.feature.tournament.di

import com.google.firebase.firestore.FirebaseFirestore
import com.lkby.feature.tournament.data.remote.tournament.FirebaseTournamentDataSource
import com.lkby.feature.tournament.data.remote.tournament.TournamentDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<TournamentDataSource> {
        FirebaseTournamentDataSource(get())
    }
}