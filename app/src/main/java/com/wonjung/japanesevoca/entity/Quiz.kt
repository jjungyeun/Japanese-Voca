package com.wonjung.japanesevoca.entity

class TableQuiz {
    companion object {
        const val tbName = "quiz"
        const val colId = "${tbName}_id"
        const val colDatetime = "study_datetime"
        const val colCategory = "category"
        const val colQuestionType = "question_type"
        const val colQuizType = "quiz_type"
    }
}

data class Quiz(
    val id: Int,
    val datetime: Int,
    val category: String,
    val questionType: String,
    val quizType: String
)