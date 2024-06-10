package com.example.myapplication.domain.usecases

import com.example.myapplication.data.model.UIFootballer
import com.example.myapplication.dispatcher.CoroutineDispatcherProvider
import com.example.myapplication.domain.repo.FootballersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetFootballersUseCase {
    operator fun invoke(page: Int): Flow<List<UIFootballer>>
}

class GetFootballersUseCaseImpl @Inject constructor(
    private val footballersRepository: FootballersRepository,
    private val dispatcher: CoroutineDispatcherProvider,
) : GetFootballersUseCase {
    override fun invoke(page: Int): Flow<List<UIFootballer>> =
        footballersRepository.getAllFootballers(page).flowOn(dispatcher.io)
}