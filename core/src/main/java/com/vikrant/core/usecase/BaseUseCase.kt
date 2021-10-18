package com.vikrant.core.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
abstract class BaseUseCase<T> {
    protected abstract fun execute() : Flow<T>
    private val flow = MutableStateFlow(true)
    val outputFlow: Flow<T> = flow.flatMapLatest{
        execute()
    }

    suspend fun launch() {
        flow.emit(!(flow.value))
    }
}