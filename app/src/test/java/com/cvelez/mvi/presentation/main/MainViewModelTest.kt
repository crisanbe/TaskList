package com.cvelez.mvi.presentation.main

import com.cvelez.mvi.domain.entities.TaskList
import com.cvelez.mvi.domain.use_case.IGetTaskListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var getTodos: IGetTaskListUseCase

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setup() = coroutinesTestRule.testDispatcher.runBlockingTest {
        Mockito.`when`(getTodos.invoke()).thenReturn(prepareData())
        viewModel = MainViewModel(getTodos, coroutinesTestRule.testDispatcher, MainScreenViewDataMapper())
    }

    @Test
    fun `addNewItem, should add a new item to the existing state`() {
        viewModel.addNewItem(
           text = "newItem"
        )

        val newItem = viewModel.state.value.data[2]
        assertEquals(4, viewModel.state.value.data.size)
        assertIs<MainScreenItem.MainScreenTodoItem>(newItem)
        assertEquals("newItem", newItem.text)
        assertFalse(newItem.isChecked)
    }

    @Test
    fun `changeAddDialogState, should show the AddItem dialog`() {
        viewModel.changeAddDialogState(true)

        assertTrue(viewModel.state.value.isShowAddDialog)
    }

    @Test
    fun `onItemCheckedChanged, first item should be checked`() {
        viewModel.onItemCheckedChanged(0, true)

        val item = viewModel.state.value.data.first()
        assertIs<MainScreenItem.MainScreenTodoItem>(item)
        assertTrue(item.isChecked)
    }

    private fun prepareData(): List<TaskList> {
        return listOf(
            TaskList(
                isChecked = false,
                text = ITEM1_ID,
            ),
            TaskList(
                isChecked = false,
                text = ITEM2_ID,
            )
        )
    }

    companion object {
        private const val ITEM1_ID = "item1"
        private const val ITEM2_ID = "item2"
    }
}