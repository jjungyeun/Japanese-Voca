package com.wonjung.japanesevoca

const val LANGUAGE = "language"
const val QUESTION_TYPE = "question_type"
const val CHAPTER_LIST = "chapters"
const val QUIZ_TYPE = "quiz_type"

enum class Language(val str: String) {
    JAPANESE("JP"), CHINESE_CHAR("CH")
}

enum class QuestionType {
    WORD_TO_MEANING, MEANING_TO_WORD, CHAR_TO_KO, CHAR_TO_JP_SOUND, CHAR_TO_JP_MEANING, ALL
}

enum class QuizType {
    RECOMMENDED, RANDOM, ALL
}