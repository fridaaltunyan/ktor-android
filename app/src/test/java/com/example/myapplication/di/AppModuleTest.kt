package com.example.myapplication.di

import dagger.Module
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import junit.framework.TestCase.assertNotNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)

@Module
class AppModuleTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var retrofit: Retrofit
    init {
        hiltRule.inject()
    }
    @Test
    fun providesLoggingInterceptor() {
        assertNotNull(loggingInterceptor)
    }

    @Test
    fun providesOkHttpClient() {
    }

    @Test
    fun providesRabbitRepository() {
    }

    @Test
    fun provideRabbitsApi() {
        assertNotNull(retrofit)
    }
}