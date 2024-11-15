package com.wonjung.japanesevoca

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.wonjung.japanesevoca.databinding.ChapterItemBinding
import com.wonjung.japanesevoca.entity.Chapter


class ChapterViewHolder(val binding: ChapterItemBinding): RecyclerView.ViewHolder(binding.root)

class ChapterAdapter(val datas: MutableList<Chapter>?, val allCheckBox: CheckBox): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var allChecked = false
    val checkedChapters = mutableSetOf<Chapter>()

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return ChapterViewHolder(ChapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as ChapterViewHolder).binding

        val myChapter = datas!![position]
        binding.checkboxChapter.text= myChapter.name
        binding.checkboxChapter.setOnCheckedChangeListener(null)

        binding.checkboxChapter.isChecked = checkedChapters.contains(myChapter)

        binding.chapterItem.setOnClickListener {
            clickBox(myChapter, binding, datas)
        }

        binding.checkboxChapter.setOnClickListener {
            clickBox(myChapter, binding, datas)
        }

    }

    private fun clickBox(
        myChapter: Chapter,
        binding: ChapterItemBinding,
        datas: MutableList<Chapter>
    ) {
        if (checkedChapters.contains(myChapter)) { // 체크 해제
            checkedChapters.remove(myChapter)
            binding.checkboxChapter.isChecked = false

            // 모두 선택 체크박스 해제
            allChecked = false
            allCheckBox.isChecked = false
        } else { // 체크
            checkedChapters.add(myChapter)
            binding.checkboxChapter.isChecked = true

            // 모두 선택 체크박스 체크
            if (datas.size == checkedChapters.size) {
                allChecked = true
                allCheckBox.isChecked = true
            }
        }
    }

    fun OnClickAllCheckBox() {
        if (!allChecked) { // 모두 선택 체크박스를 체크하는 경우
            allChecked = true
            checkedChapters.addAll(datas!!)
        } else {
            allChecked = false
            checkedChapters.clear()
        }
    }

}
