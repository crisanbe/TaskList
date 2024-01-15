package com.cvelez.mvi.domain.repository

import arrow.core.Either
import com.cvelez.mvi.data.TaskListDataSource
import com.cvelez.mvi.domain.entities.TaskList
import com.cvelez.mvi.domain.mapper.TaskListMapper


interface ITaskListRepository {
    suspend fun getTaskList(): Either<Throwable, List<TaskList>>
}

class TaskListRepository(
    private val taskListMapper: TaskListMapper,
    private val taskListDataSource: TaskListDataSource
) : ITaskListRepository {

    override suspend fun getTaskList(): Either<Throwable, List<TaskList>> {
        return taskListDataSource.getTaskList()
            .map { it.map(taskListMapper::mapFromLocal) }
    }
}
