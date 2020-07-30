package ru.ozh.recycler.decorator.timeline

import androidx.annotation.DrawableRes
import kotlin.random.Random

data class Event(
    val id: Int = Random.nextInt(),
    val eventName: String,
    val status: Status,
    val gravity: Int,
    @DrawableRes
    val icon: Int
)

enum class Status {
    DONE,
    PROCESS,
    UNDONE,
    UNDEFINE
}