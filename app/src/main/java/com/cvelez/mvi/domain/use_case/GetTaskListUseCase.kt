package com.cvelez.mvi.domain.use_case

import arrow.core.Either
import com.cvelez.mvi.domain.entities.TaskList
import com.cvelez.mvi.domain.repository.TaskListRepository

interface IGetTaskListUseCase: CoroutineUseCase<Either<Throwable, List<TaskList>>, Unit>

class GetTaskListUseCase (
    private val taskListRepository: TaskListRepository
): IGetTaskListUseCase {

    override suspend fun invoke(parameters: Unit?): Either<Throwable, List<TaskList>>{
        return taskListRepository.getTaskList()
    }
}