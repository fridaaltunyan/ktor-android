package com.example.myapplication.ui

sealed interface FootballerIntent {
    data object LoadFootballers : FootballerIntent
    data class ClickOnSingleItem(val name: String) : FootballerIntent
}