package com.wonjung.japanesevoca

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wonjung.japanesevoca.databinding.ActivityQuizBinding
import com.wonjung.japanesevoca.databinding.ActivitySelectQuizBinding
import com.wonjung.japanesevoca.db.DBHepler
import com.wonjung.japanesevoca.entity.TableWord
import com.wonjung.japanesevoca.entity.Word

class QuizActivity : AppCompatActivity() {
    var words = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val language = intent.intentSerializable(LANGUAGE, Language::class.java)
        val questionType = intent.intentSerializable(QUESTION_TYPE, QuestionType::class.java)
        val quizType = intent.intentSerializable(QUIZ_TYPE, QuizType::class.java)
        val chapterIds = intent.getIntegerArrayListExtra(CHAPTER_LIST)

        // 챕터에 해당하는 단어 중 퀴즈 타입에 맞춰서 조회하기
        val db = DBHepler(this).readableDatabase
        var sql = "select * from ${TableWord.tbName} where ${TableWord.colType} = '${language?.str}'"
        if (chapterIds?.get(0) != -1) {
            val chapterIdString = chapterIds?.joinToString(",")
            sql = sql.plus(" and ${TableWord.colChapterId} in (${chapterIdString})")
        }

        val cursor = db.rawQuery(sql, arrayOf())
        while (cursor.moveToNext()) {
            words.add(
                Word(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11)
            )
            )
        }
        cursor.close()

        Log.d("WON_DB", words.toString())

        // 조회한 단어 리스트를 뷰에 매핑하고 보여주기
        // 각 단어마다 버튼을 누르면 바로 History 저장하고 다음 단어로 넘어가기

        // 모든 단어 공부 끝나면 결과 팝업 띄우기
        // 액티비티 전부 끄고 홈화면으로 돌아가기
    }
}