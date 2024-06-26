package com.example.myapplication.data.service

import com.example.myapplication.data.model.DataResponse
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
    ): DataResponse<Footballer>

    companion object {
        const val BASE_URL = "http://192.168.1.54:8200"
    }
}