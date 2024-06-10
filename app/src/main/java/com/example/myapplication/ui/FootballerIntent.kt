package com.example.myapplication.ui

sealed interface FootballerIntent {
    data object LoadFootballers : FootballerIntent
}