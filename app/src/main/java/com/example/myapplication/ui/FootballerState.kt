package com.example.myapplication.ui

import com.example.myapplication.data.model.UIFootballer

data class FootballerState(
    val loading: Boolean = false,
    val footballers: List<UIFootballer> = emptyList(),
    val error: String? = null,
    val singleFootballer: UIFootballer? = null,
)
