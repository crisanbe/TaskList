package com.cvelez.mvi.presentation.main

import com.cvelez.mvi.base.UiEvent
import com.cvelez.mvi.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
sealed class MainScreenUiEvent : UiEvent {
    data class ShowData(val items: List<MainScreenItem>) : MainScreenUiEvent()
    data class OnChangeDialogState(val show: Boolean) : MainScreenUiEvent()
    data class AddNewItem(val text: String) : MainScreenUiEvent()
    data class DeleteItem(val index: Int) : MainScreenUiEvent()
    data class OnItemCheckedChanged(val index: Int, val isChecked: Boolean) : MainScreenUiEvent()
    object DismissDialog : MainScreenUiEvent()
}

@Immutable
data class MainScreenState(
    val isLoading: Boolean,
    val data: List<MainScreenItem>,
    val deleteItemIndex: Int? = null,
    val isShowAddDialog: Boolean,
) : UiState {

    fun resetDeleteItemIndex(): MainScreenState {
        return copy(deleteItemIndex = null)
    }
    companion object {
        fun initial() = MainScreenState(
            isLoading = true,
            data = emptyList(),
            isShowAddDialog = false
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}, isShowAddDialog: $isShowAddDialog"
    }
}