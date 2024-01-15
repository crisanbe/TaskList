package com.cvelez.mvi.domain.use_case

import com.cvelez.mvi.domain.entities.TaskList
import com.cvelez.mvi.domain.repository.TaskListRepository
import javax.inject.Inject

interface IGetTaskListUseCase: CoroutineUseCase<List<TaskList>, Unit>

class GetTaskListUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): IGetTaskListUseCase {

    override suspend fun invoke(parameters: Unit?): List<TaskList> {
        return taskListRepository.getTaskList()
    }
}