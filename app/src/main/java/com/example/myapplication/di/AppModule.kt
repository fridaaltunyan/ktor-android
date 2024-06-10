package com.example.myapplication.di

import com.example.myapplication.data.repo_impl.FootballersRepositoryImpl
import com.example.myapplication.data.service.FootballersApi
import com.example.myapplication.data.service.FootballersApi.Companion.BASE_URL
import com.example.myapplication.dispatcher.CoroutineDispatcherProvider
import com.example.myapplication.dispatcher.CoroutineDispatcherProviderImpl
import com.example.myapplication.domain.repo.FootballersRepository
import com.example.myapplication.domain.usecases.GetFootballersUseCase
import com.example.myapplication.domain.usecases.GetFootballersUseCaseImpl
import com.example.myapplication.domain.usecases.GetSingleFootballerUseCase
import com.example.myapplication.domain.usecases.GetSingleFootballerUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .apply {
            addInterceptor(
                httpLoggingInterceptor
            )
        }
        .build()

    @Provides
    @Singleton
    fun provideFootballerRepository(
        footballersApi: FootballersApi,
    ): FootballersRepository = FootballersRepositoryImpl(footballersApi)

    @Provides
    @Singleton
    fun provideFootballerApi(okHttpClient: OkHttpClient): FootballersApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(FootballersApi::class.java)
    }

    @Provides
    fun provideGetFootballersUseCase(
        footballersRepository: FootballersRepository,
        provider: CoroutineDispatcherProvider
    ): GetFootballersUseCase =
        GetFootballersUseCaseImpl(footballersRepository, provider)

    @Provides
    fun provideGetSingleFootballerUseCase(
        footballersRepository: FootballersRepository,
        provider: CoroutineDispatcherProvider
    ): GetSingleFootballerUseCase =
        GetSingleFootballerUseCaseImpl(footballersRepository, provider)

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider =
        CoroutineDispatcherProviderImpl()
}