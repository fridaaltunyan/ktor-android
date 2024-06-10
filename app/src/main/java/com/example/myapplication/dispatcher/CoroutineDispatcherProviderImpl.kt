package com.example.myapplication.dispatcher

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineDispatcherProviderImpl : CoroutineDispatcherProvider {
    override val main: CoroutineContext by lazy { Dispatchers.Main }
    override val io: CoroutineContext by lazy { Dispatchers.IO }
}