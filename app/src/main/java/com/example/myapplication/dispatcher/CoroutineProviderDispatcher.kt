package com.example.myapplication.dispatcher

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatcherProvider {
    val main: CoroutineContext
    val io: CoroutineContext
}