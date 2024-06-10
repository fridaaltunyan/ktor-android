package com.example.myapplication.data.model

data class DataResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val footballers: List<T> = emptyList(),
    val lastUpdated: Long? = null
)