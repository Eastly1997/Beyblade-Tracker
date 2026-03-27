package com.lkby.common.di

import com.lkby.common.util.IdProvider
import com.lkby.common.util.NanoIdProvider
import org.koin.dsl.module

val utilModule = module {
    single<IdProvider> { NanoIdProvider() }
}