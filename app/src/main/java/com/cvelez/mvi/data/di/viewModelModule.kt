package com.cvelez.mvi.data.di

import com.cvelez.mvi.domain.use_case.GetTaskListUseCase
import com.cvelez.mvi.domain.use_case.IGetTaskListUseCase
import com.cvelez.mvi.presentation.main.MainScreenViewDataMapper
import com.cvelez.mvi.presentation.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

        // ... (other dependencies)

        // Provide IGetTaskListUseCase, Dispatcher, and other dependencies if needed
        factory { Dispatchers.Main }
        factory<IGetTaskListUseCase> { GetTaskListUseCase(get()) }

        // Provide MainViewModel using viewModel function
        viewModel { MainViewModel(get(), get(), MainScreenViewDataMapper()) }
}

