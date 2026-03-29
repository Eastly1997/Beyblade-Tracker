package com.lkby.feature.auth.di

import com.lkby.feature.auth.data.repository.AuthRepositoryImpl
import com.lkby.feature.auth.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
}

