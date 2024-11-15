package com.wonjung.japanesevoca.entity

class TableChapter {
    companion object {
        const val tbName = "chapter"
        const val colId = "${tbName}_id"
        const val colCategory = "category"
        const val colName = "name"

        fun insert(category: String, name: String): String {
            return "INSERT INTO ${tbName} (" +
                    "${colId}, ${colCategory}, ${colName}" +
                    ") VALUES (" +
                    "null, '${category}', '${name}')"
        }
    }
}

data class Chapter(
    val id: Int,
    val category: String,
    val name: String
)