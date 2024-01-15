package com.cvelez.mvi.app

import io.realm.RealmConfiguration
import org.koin.dsl.module

val appModule = module {

    single { provideRealmDefaultConfiguration() }
}

private fun provideRealmDefaultConfiguration(): RealmConfiguration {
    return RealmConfiguration.Builder()
        .name("mviTaskListDb")
        .allowQueriesOnUiThread(true)
        .allowWritesOnUiThread(true)
        .build()
}
