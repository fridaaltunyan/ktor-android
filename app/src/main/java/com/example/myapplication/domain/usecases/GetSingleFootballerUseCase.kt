package com.example.myapplication.domain.usecases

import com.example.myapplication.data.model.UIFootballer
import com.example.myapplication.dispatcher.CoroutineDispatcherProvider
import com.example.myapplication.domain.repo.FootballersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetSingleFootballerUseCase {
    operator fun invoke(name: String): Flow<UIFootballer>
}

class GetSingleFootballerUseCaseImpl @Inject constructor(
    private val footballersRepository: FootballersRepository,
    private val dispatcher: CoroutineDispatcherProvider,
) : GetSingleFootballerUseCase {
    override fun invoke(name: String): Flow<UIFootballer> =
        footballersRepository.getFootballer(name).flowOn(
            dispatcher.io
        )

}