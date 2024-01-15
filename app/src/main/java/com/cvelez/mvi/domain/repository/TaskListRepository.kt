package com.cvelez.mvi.domain.repository

import com.cvelez.mvi.data.TaskListDataSource
import com.cvelez.mvi.domain.entities.TaskList
import com.cvelez.mvi.domain.mapper.TaskListMapper

interface ITaskListRepository {
    suspend fun getTaskList(): List<TaskList>
}

class TaskListRepository (
    private val todoMapper: TaskListMapper,
    private val taskListDataSource: TaskListDataSource
): ITaskListRepository {

    override suspend fun getTaskList(): List<TaskList> {
        return taskListDataSource.getTaskList().map(todoMapper::mapFromLocal)
    }

}