package com.cvelez.mvi.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcherModule = module {

    single<CoroutineDispatcher> {
        Dispatchers.Default
    }
}
