package com.wonjung.japanesevoca.entity

import com.wonjung.japanesevoca.Language

class TableWord {
    companion object {
        const val tbName = "word"
        const val colId = "${tbName}_id"
        const val colOrigin = "origin"
        const val colEtc = "etc"
        const val colDatetime = "last_study_datetime"
        const val colType = "${tbName}_type"
        const val colPron = "pron"
        const val colMeaning = "meaning"
        const val colNum = "ch_num"
        const val colKo = "korean"
        const val colJpSound = "jp_sound"
        const val colJpMean = "jp_meaning"
        const val colChapterId = "chapter_id"

        fun insertJp(origin: String, etc: String, pron: String, mean: String, chapterId: Int): String {
            return "INSERT INTO ${tbName} (" +
                    "${colId}, ${colOrigin}, ${colEtc}, ${colType}, ${colPron}, ${colMeaning}, ${colChapterId}" +
                    ") VALUES (" +
                    "null, '${origin}', '${etc}', '${Language.JAPANESE.str}', '${pron}', '${mean}', ${chapterId})"
        }
    }
}

data class Word(
    val id: Int,
    val origin: String,
    val chapterId: Int,
    val type: String,
    val etc: String?,
    val datetime: Int?,
    val pron: String?,
    val meaning: String?,
    val num: String?,
    val ko: String?,
    val jpSound: String?,
    val jpMean: String?
)