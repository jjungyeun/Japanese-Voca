package com.wonjung.japanesevoca.entity

class TableHistory {
    companion object {
        const val tbName = "history"
        const val colId = "${tbName}_id"
        const val colWord = "word_id"
        const val colQuiz = "quiz_id"
        const val colScore = "score"
    }
}

data class History(
    val id: Int,
    val word: Int,
    val quiz: Int,
    val score: Int
)