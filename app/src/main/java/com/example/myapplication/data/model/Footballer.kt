package com.example.myapplication.data.model

data class Footballer(
    val id: Long,
    val name: String,
    val position: String,
    val clubName: String,
    val imageUrl: String,
)

data class UIFootballer(
    val id: Long,
    val name: String,
    val position: String,
    val clubName: String,
    val imageUrl: String,
)

fun Footballer.toUIFootballer() = UIFootballer(
    id = id,
    name = name,
    position = position,
    clubName = clubName,
    imageUrl = imageUrl
)