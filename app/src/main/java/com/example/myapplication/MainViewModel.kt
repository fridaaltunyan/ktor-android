package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecases.GetFootballersUseCase
import com.example.myapplication.domain.usecases.GetSingleFootballerUseCase
import com.example.myapplication.ui.FootballerIntent
import com.example.myapplication.ui.FootballerState
import com.example.myapplication.utils.subscribe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFootballersUseCase: GetFootballersUseCase,
    private val getSingleFootballerUseCase: GetSingleFootballerUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(FootballerState())
    val state: StateFlow<FootballerState> = _state

    init {
        getAllFootballers()
    }

    fun handleIntent(intent: FootballerIntent) {
        viewModelScope.launch {
            when (intent) {
                is FootballerIntent.LoadFootballers -> getAllFootballers()
                // ... Other Intents goes here...
            }
        }
    }

    private fun getAllFootballers() {
        getFootballersUseCase.invoke(1).subscribe(
            viewModelScope,
            error = {
                _state.update {
                    it.copy(loading = false, footballers = emptyList(), error = it.error)
                }
            },
            onStart = {
                _state.update {
                    it.copy(loading = true)
                }
            },
            success = { footballers ->
                _state.update {
                    it.copy(loading = false, footballers = footballers, error = null)
                }
            },
        )

    }

    fun getSingleFootballer(name: String) {
        getSingleFootballerUseCase(name).subscribe(
            viewModelScope,
            error = {},
            onStart = {},
            success = {},
        )
    }


}