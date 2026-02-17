package com.lkby.tracker.di

import com.lkby.tracker.domain.usecase.CreateTournamentUseCase
import com.lkby.tracker.domain.usecase.SignInWithGoogleUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory{
        CreateTournamentUseCase(get())
    }

    factory{
        SignInWithGoogleUseCase(get())
    }
}