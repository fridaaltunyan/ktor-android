package com.example.myapplication.data.repo_impl

import com.example.myapplication.data.model.UIFootballer
import com.example.myapplication.data.model.toUIFootballer
import com.example.myapplication.data.service.FootballersApi
import com.example.myapplication.domain.repo.FootballersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FootballersRepositoryImpl @Inject constructor(
    private val footballersApi: FootballersApi,
) : FootballersRepository {
    override fun getAllFootballers(page: Int): Flow<List<UIFootballer>> {
        return flow { emit(footballersApi.getAllFootballers(page).footballers.map { it.toUIFootballer() }) }
    }

    override fun getFootballer(name: String): Flow<UIFootballer> {
        return flow { emit(footballersApi.getFootballer(name).toUIFootballer()) }
    }

}
