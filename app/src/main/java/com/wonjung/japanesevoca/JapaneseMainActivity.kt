package com.wonjung.japanesevoca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wonjung.japanesevoca.databinding.ActivityJapaneseMainBinding

class JapaneseMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityJapaneseMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJapaneseQuiz1.setOnClickListener {
            val intent = Intent(this, SelectChapterActivity::class.java)
            intent.putExtra(LANGUAGE, Language.JAPANESE)
            intent.putExtra(QUESTION_TYPE, QuestionType.WORD_TO_MEANING)
            startActivity(intent)
        }

        binding.btnJapaneseQuiz2.setOnClickListener {
            val intent = Intent(this, SelectChapterActivity::class.java)
            intent.putExtra(LANGUAGE, Language.JAPANESE)
            intent.putExtra(QUESTION_TYPE, QuestionType.MEANING_TO_WORD)
            startActivity(intent)
        }

        binding.btnJapaneseQuiz2.setOnClickListener {
            // 모든 단어 리스트 액티비티 실행
        }
    }
}