package com.lkby.feature.auth.di

val authModule = listOf(
    firebaseModule,
    remoteDataSourceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)