package com.cvelez.mvi.domain.use_case

import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule = module {
    // Provide GetTaskListUseCase
    factory { GetTaskListUseCase(get()) } bind IGetTaskListUseCase::class
}
