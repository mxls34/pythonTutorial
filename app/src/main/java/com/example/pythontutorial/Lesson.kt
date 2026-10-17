package com.example.pythontutorial

data class Lesson(
    val title: String,
    val content: Int,
    val choices: Array<String>? = null,
    val correct: Int = -1
)