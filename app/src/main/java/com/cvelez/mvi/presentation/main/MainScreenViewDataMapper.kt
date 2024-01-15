package com.cvelez.mvi.presentation.main

import com.cvelez.mvi.domain.entities.TaskList
import javax.inject.Inject

class MainScreenViewDataMapper @Inject constructor() {

    fun buildScreen(todos: List<TaskList>): List<MainScreenItem> {
        val viewData = mutableListOf<MainScreenItem>()
        viewData.addAll(todos.map { todo -> MainScreenItem.MainScreenTodoItem(todo.isChecked, todo.text) })
        viewData.add(MainScreenItem.MainScreenAddButtonItem)
        return viewData
    }
}