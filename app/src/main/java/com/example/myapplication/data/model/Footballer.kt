package com.example.myapplication.data.model

data class Footballer(
    val id: Long,
    val name: String,
    val position: FootballerPositionEnum,
    val clubName: String,
    val imageUrl: String,
)

data class UIFootballer(
    val id: Long,
    val name: String,
    val position: UIFootballerPositionEnum,
    val clubName: String,
    val imageUrl: String,
)

enum class FootballerPositionEnum {
    GOALKEEPER,
    DEFENDER,
    MIDFIELDER,
    FORWARD
}

enum class UIFootballerPositionEnum {
    GOALKEEPER,
    DEFENDER,
    MIDFIELDER,
    FORWARD
}

fun FootballerPositionEnum.toUIFootballerPositionEnum() = when (this) {
    FootballerPositionEnum.GOALKEEPER -> UIFootballerPositionEnum.GOALKEEPER
    FootballerPositionEnum.DEFENDER -> UIFootballerPositionEnum.DEFENDER
    FootballerPositionEnum.MIDFIELDER -> UIFootballerPositionEnum.MIDFIELDER
    FootballerPositionEnum.FORWARD -> UIFootballerPositionEnum.FORWARD
}

fun Footballer.toUIFootballer() = UIFootballer(
    id = id,
    name = name,
    position = position.toUIFootballerPositionEnum(),
    clubName = clubName,
    imageUrl = imageUrl
)