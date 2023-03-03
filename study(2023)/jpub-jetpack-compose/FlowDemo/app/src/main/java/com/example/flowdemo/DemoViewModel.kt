package com.example.flowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<Int>(
        replay = 10,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val sharedFlow = _sharedFlow.asSharedFlow()

    val subCount = _sharedFlow.subscriptionCount

    val myFlow: Flow<Int> = flow {
        for (i in 1..5) {
            emit(i)
            delay(1000)
        }
    }

    val hotFlow = myFlow.shareIn(
        viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    fun startSharedFlow() {
        viewModelScope.launch {
            for (i in 1..5) {
                _sharedFlow.emit(i)
                delay(1000)
            }
        }
    }
}