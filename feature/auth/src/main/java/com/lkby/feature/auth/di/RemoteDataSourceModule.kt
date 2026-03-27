package com.lkby.feature.auth.di

import com.lkby.feature.auth.data.remote.auth.AuthDataSource
import com.lkby.feature.auth.data.remote.auth.FirebaseAuthDataSource
import com.lkby.feature.auth.data.remote.user.FireStoreUserDataSource
import com.lkby.feature.auth.data.remote.user.UserDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {

    single<AuthDataSource> {
        FirebaseAuthDataSource(get())
    }

    single<UserDataSource> {
        FireStoreUserDataSource(get())
    }
}