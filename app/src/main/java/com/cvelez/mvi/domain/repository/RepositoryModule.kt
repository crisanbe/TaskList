package com.cvelez.mvi.domain.repository

import com.cvelez.mvi.data.TaskListDataSource
import com.cvelez.mvi.data.TaskListLocalDataSource
import com.cvelez.mvi.domain.mapper.TaskListMapper
import org.koin.dsl.bind

import org.koin.dsl.module

val repositoryModule = module {
    single<TaskListMapper> { TaskListMapper() } bind TaskListMapper::class
    single<TaskListDataSource> { TaskListLocalDataSource(get()) } bind TaskListDataSource::class
    single<TaskListRepository> { TaskListRepository(get(), get()) }
}

