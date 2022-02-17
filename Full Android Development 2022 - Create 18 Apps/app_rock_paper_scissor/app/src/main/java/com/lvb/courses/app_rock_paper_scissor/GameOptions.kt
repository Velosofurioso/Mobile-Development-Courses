package com.lvb.courses.app_rock_paper_scissor

enum class GameOptions {
    PAPER,
    ROCK,
    SCISSOR;

    companion object {
        fun fromInt(value: Int) = values().firstOrNull() {it.ordinal == value}
    }
}