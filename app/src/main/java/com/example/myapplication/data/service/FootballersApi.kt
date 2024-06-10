package com.example.myapplication.data.service

import com.example.myapplication.data.model.Footballer
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballersApi {
    @GET("/footballer")
    suspend fun getFootballer(
        @Query("name") name: String?,
    ): Footballer

    @GET("/footballers/all")
    suspend fun getAllFootballers(
        @Query("page") page: Int?,
    ): List<Footballer>

    companion object {
        const val BASE_URL = "http://localhost:8200"
    }
}