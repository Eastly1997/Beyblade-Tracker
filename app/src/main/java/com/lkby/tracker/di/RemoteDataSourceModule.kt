package com.lkby.tracker.di

import com.lkby.tracker.data.remote.auth.AuthDataSource
import com.lkby.tracker.data.remote.auth.firebase.FirebaseAuthDataSource
import com.lkby.tracker.data.remote.tournament.firebase.FirebaseTournamentDataSource
import com.lkby.tracker.data.remote.tournament.TournamentDataSource
import com.lkby.tracker.data.remote.user.UserDataSource
import com.lkby.tracker.data.remote.user.firebase.FireStoreUserDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<TournamentDataSource> {
        FirebaseTournamentDataSource(get())
    }

    single<AuthDataSource> {
        FirebaseAuthDataSource(get())
    }

    single<UserDataSource> {
        FireStoreUserDataSource(get())
    }
}