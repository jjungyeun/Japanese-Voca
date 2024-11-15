package com.wonjung.japanesevoca

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wonjung.japanesevoca.databinding.ActivitySelectChapterBinding
import com.wonjung.japanesevoca.db.DBHepler
import com.wonjung.japanesevoca.entity.Chapter
import com.wonjung.japanesevoca.entity.TableChapter
import java.io.Serializable

class SelectChapterActivity : AppCompatActivity() {
    var chapters = mutableListOf<Chapter>()
    lateinit var adapter: ChapterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySelectChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val language = intent.intentSerializable(LANGUAGE, Language::class.java)
        val questionType = intent.intentSerializable(QUESTION_TYPE, QuestionType::class.java)

        val db = DBHepler(this).readableDatabase
        val cursor = db.rawQuery(
            "select * from ${TableChapter.tbName} " +
                    "where ${TableChapter.colCategory} = ?", arrayOf(language?.str)
        )

        while (cursor.moveToNext()) {
            chapters.add(Chapter(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
            ))
        }
        cursor.close()

        binding.rvChapterList.layoutManager = LinearLayoutManager(this)
        adapter = ChapterAdapter(chapters, binding.checkboxChapterAll)
        binding.rvChapterList.adapter = adapter
        binding.rvChapterList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        // 모두 선택 체크박스 리스너
        binding.checkboxChapterAll.setOnClickListener {
            adapter.OnClickAllCheckBox()
            adapter.notifyDataSetChanged()
        }


        // 선택 완료 버튼 리스너
        // 언어, 질문 유형, 선택한 챕터 아이디 리스트를 인텐트에 같이 넘김
        binding.btnChapterSelect.setOnClickListener {
            Log.d("WON_DB", adapter.checkedChapters.toString())
            if (adapter.checkedChapters.isEmpty()) {
                Toast.makeText(this, "챕터를 한개 이상 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SelectQuizActivity::class.java)
                intent.putExtra(LANGUAGE, language)
                intent.putExtra(QUESTION_TYPE, questionType)

                val selectedIds = if (adapter.checkedChapters.size == chapters.size) {
                    listOf(-1)
                } else {
                    adapter.checkedChapters.map { it.id }
                }
                intent.putIntegerArrayListExtra(CHAPTER_LIST, ArrayList(selectedIds))

                startActivity(intent)
            }
        }
    }
}

fun <T: Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}