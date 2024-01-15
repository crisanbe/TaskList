package com.cvelez.mvi.presentation.main

sealed class MainScreenItem {
    object MainScreenAddButtonItem : MainScreenItem()
    data class MainScreenTodoItem(
        val isChecked: Boolean,
        val text: String,
    ) : MainScreenItem()
}
