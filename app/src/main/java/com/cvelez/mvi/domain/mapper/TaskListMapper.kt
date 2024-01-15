package com.cvelez.mvi.domain.mapper

import com.cvelez.mvi.data.model.TaskListLocal
import com.cvelez.mvi.domain.entities.TaskList
import java.util.*

class TaskListMapper () {

    fun mapToLocal(todo: TaskList) = TaskListLocal().apply {
        id = UUID.randomUUID().toString()
        text = todo.text
        isChecked = todo.isChecked
    }

    fun mapFromLocal(taskListLocal: TaskListLocal) = TaskList(
        text = taskListLocal.text,
        isChecked = taskListLocal.isChecked
    )
}