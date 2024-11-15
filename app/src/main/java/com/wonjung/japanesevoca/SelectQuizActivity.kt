package com.wonjung.japanesevoca

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wonjung.japanesevoca.databinding.ActivitySelectChapterBinding
import com.wonjung.japanesevoca.databinding.ActivitySelectQuizBinding

class SelectQuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySelectQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val language = intent.intentSerializable(LANGUAGE, Language::class.java)
        val questionType = intent.intentSerializable(QUESTION_TYPE, QuestionType::class.java)
        val chapterIds = intent.getIntegerArrayListExtra(CHAPTER_LIST)

        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra(LANGUAGE, language)
        intent.putExtra(QUESTION_TYPE, questionType)
        intent.putIntegerArrayListExtra(CHAPTER_LIST, chapterIds)

        binding.btnQuizRecommended.setOnClickListener {
            intent.putExtra(QUIZ_TYPE, QuizType.RECOMMENDED)
            startActivity(intent)
        }

        binding.btnQuizRandom.setOnClickListener {
            intent.putExtra(QUIZ_TYPE, QuizType.RANDOM)
            startActivity(intent)
        }

        binding.btnQuizAll.setOnClickListener {
            intent.putExtra(QUIZ_TYPE, QuizType.ALL)
            startActivity(intent)
        }
    }
}