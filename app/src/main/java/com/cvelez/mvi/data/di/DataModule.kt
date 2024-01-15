package com.cvelez.mvi.data.di

import com.cvelez.mvi.data.TaskListDataSource
import com.cvelez.mvi.data.TaskListLocalDataSource
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module

val dataModule = module {

    // Bind TaskListDataSource to TaskListLocalDataSource
    factory<TaskListDataSource> { TaskListLocalDataSource(get()) } bind TaskListDataSource::class
}