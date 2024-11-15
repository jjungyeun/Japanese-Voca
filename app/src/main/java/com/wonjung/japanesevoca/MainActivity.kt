package com.wonjung.japanesevoca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wonjung.japanesevoca.databinding.ActivityMainBinding
import com.wonjung.japanesevoca.db.DBHepler
import com.wonjung.japanesevoca.entity.TableWord

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHomeJapanese.setOnClickListener {
            val intent = Intent(this, JapaneseMainActivity::class.java)
            startActivity(intent)
        }

        binding.btnHomeChineseChar.setOnClickListener {
            val intent = Intent(this, ChineseCharMainActivity::class.java)
            startActivity(intent)
        }

    }
}