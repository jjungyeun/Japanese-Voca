package com.wonjung.japanesevoca.db

import android.content.Context
import android.content.res.AssetManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.wonjung.japanesevoca.Language
import com.wonjung.japanesevoca.entity.TableChapter
import com.wonjung.japanesevoca.entity.TableHistory
import com.wonjung.japanesevoca.entity.TableQuiz
import com.wonjung.japanesevoca.entity.TableWord
import java.io.InputStream

class DBHepler(private val context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "testdb_1"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_CHAPTER_TABLE = "CREATE TABLE ${TableChapter.tbName} (" +
                "${TableChapter.colId} integer primary key autoincrement, " +
                "${TableChapter.colCategory} text not null, " +
                "${TableChapter.colName} text not null)"
        Log.d("WON_DB", "Chapter 테이블 생성 쿼리: $CREATE_CHAPTER_TABLE")
        db?.execSQL(CREATE_CHAPTER_TABLE)

        val CREATE_WORD_TABLE = "CREATE TABLE ${TableWord.tbName} (" +
                "${TableWord.colId} integer primary key autoincrement," +
                "${TableWord.colOrigin} text not null," +
                "${TableWord.colChapterId} integer not null," +
                "${TableWord.colType} text not null," +
                "${TableWord.colEtc} text," +
                "${TableWord.colDatetime} integer," +
                "${TableWord.colPron} text," +
                "${TableWord.colMeaning} text," +
                "${TableWord.colNum} text," +
                "${TableWord.colKo} text," +
                "${TableWord.colJpSound} text," +
                "${TableWord.colJpMean} text," +
                "constraint ${TableWord.tbName}_${TableChapter.tbName}_fk foreign key " +
                "(${TableWord.colChapterId}) references ${TableChapter.tbName}(${TableChapter.colId}))"
        Log.d("WON_DB", "Word 테이블 생성 쿼리: $CREATE_WORD_TABLE")
        db?.execSQL(CREATE_WORD_TABLE)

        val CREATE_QUIZ_TABLE = "CREATE TABLE ${TableQuiz.tbName} (" +
                "${TableQuiz.colId} integer primary key autoincrement, " +
                "${TableQuiz.colDatetime} integer not null, " +
                "${TableQuiz.colCategory} text not null, " +
                "${TableQuiz.colQuizType} text not null, " +
                "${TableQuiz.colQuestionType} text not null)"
        Log.d("WON_DB", "Quiz 테이블 생성 쿼리: $CREATE_QUIZ_TABLE")
        db?.execSQL(CREATE_QUIZ_TABLE)

        val CREATE_HISTORY_TABLE = "CREATE TABLE ${TableHistory.tbName} (" +
                "${TableHistory.colId} integer primary key autoincrement," +
                "${TableHistory.colWord} integer not null," +
                "${TableHistory.colQuiz} integer not null," +
                "${TableHistory.colScore} integer not null," +
                "constraint ${TableHistory.tbName}_${TableWord.tbName}_fk foreign key " +
                "(${TableHistory.colWord}) references ${TableWord.tbName}(${TableWord.colId})," +
                "constraint ${TableHistory.tbName}_${TableQuiz.tbName}_fk foreign key " +
                "(${TableHistory.colQuiz}) references ${TableQuiz.tbName}(${TableQuiz.colId}))"
        Log.d("WON_DB", "History 테이블 생성 쿼리: $CREATE_HISTORY_TABLE")
        db?.execSQL(CREATE_HISTORY_TABLE)

        loadWordFile(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    private fun loadWordFile(db: SQLiteDatabase?) {
        val assetManager: AssetManager = context.assets
        val inputStream: InputStream = assetManager.open("words.tsv")

        val chapterMap = mutableMapOf<String, Int>() // chapter name -> chapter id

        inputStream.bufferedReader().readLines().forEach { line ->
            val token = line.split("\t")
            val chapterStr = token[0]
            val word = token[1]
            val pron = token[2]
            val mean = token[3]
            val etc = token[4]

            val chapterId: Int = if (chapterMap.containsKey(chapterStr)) {
                chapterMap[chapterStr]!!
            } else {
                val category = Language.JAPANESE.str
                db?.execSQL(TableChapter.insert(category, chapterStr))
                val cursor = db?.rawQuery(
                    "SELECT ${TableChapter.colId} FROM ${TableChapter.tbName} " +
                            "WHERE ${TableChapter.colCategory} = ? and ${TableChapter.colName} = ?",
                    arrayOf(category, chapterStr))
                cursor?.moveToFirst()
                val id: Int? = cursor?.getInt(0)
                cursor?.close()
                chapterMap[chapterStr] = id !!
                id
            }

            db?.execSQL(TableWord.insertJp(word, etc, pron, mean, chapterId))

//            Log.d("WON_DB", "${word} is inserted into chapter ${chapterId}")
        }

    }

}