package com.example.myapplication.domain.repo

import com.example.myapplication.data.model.UIFootballer
import kotlinx.coroutines.flow.Flow

interface FootballersRepository {
    fun getAllFootballers(page: Int): Flow<List<UIFootballer>>
    fun getFootballer(name: String): Flow<UIFootballer>
}