package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecases.GetFootballersUseCase
import com.example.myapplication.domain.usecases.GetSingleFootballerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFootballersUseCase: GetFootballersUseCase,
    private val getSingleFootballerUseCase: GetSingleFootballerUseCase,
) : ViewModel() {


    init {
        getAllFootballers()
    }

    private fun getAllFootballers() {


    }

    fun getSingleFootballer() {

    }


}