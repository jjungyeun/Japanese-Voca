package com.wonjung.japanesevoca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wonjung.japanesevoca.databinding.ActivityChineseCharMainBinding

class ChineseCharMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityChineseCharMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChineseCharQuiz1.setOnClickListener {
            val intent = Intent(this, SelectChapterActivity::class.java)
            intent.putExtra(LANGUAGE, Language.CHINESE_CHAR)
            intent.putExtra(QUESTION_TYPE, QuestionType.CHAR_TO_KO)
            startActivity(intent)
        }

        binding.btnChineseCharQuiz2.setOnClickListener {
            val intent = Intent(this, SelectChapterActivity::class.java)
            intent.putExtra(LANGUAGE, Language.CHINESE_CHAR)
            intent.putExtra(QUESTION_TYPE, QuestionType.CHAR_TO_JP_SOUND)
            startActivity(intent)
        }

        binding.btnChineseCharQuiz3.setOnClickListener {
            val intent = Intent(this, SelectChapterActivity::class.java)
            intent.putExtra(LANGUAGE, Language.CHINESE_CHAR)
            intent.putExtra(QUESTION_TYPE, QuestionType.CHAR_TO_JP_MEANING)
            startActivity(intent)
        }

        binding.btnChineseCharQuiz4.setOnClickListener {
            val intent = Intent(this, SelectChapterActivity::class.java)
            intent.putExtra(LANGUAGE, Language.CHINESE_CHAR)
            intent.putExtra(QUESTION_TYPE, QuestionType.WORD_TO_MEANING)
            startActivity(intent)
        }

        binding.btnChineseCharAll.setOnClickListener {
            // 모든 단어 리스트 액티비티 실행
        }
    }
}