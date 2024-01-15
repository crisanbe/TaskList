package com.cvelez.mvi.data.di

import com.cvelez.mvi.data.TaskListDataSource
import com.cvelez.mvi.data.TaskListLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    @Binds
    @ViewModelScoped
    fun bindTaskListDataSource(taskListDataSource: TaskListLocalDataSource): TaskListDataSource

}