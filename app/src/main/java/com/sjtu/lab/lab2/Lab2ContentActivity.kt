package com.sjtu.lab.lab2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.sjtu.lab.R

class Lab2ContentActivity : AppCompatActivity() {

    companion object {
        const val RESULT_CODE_LAB2_CONTENT_ACTIVITY = 2001
    }

    private val tvContent by lazy { findViewById<TextView>(R.id.tv_content) }
    private val etContent by lazy { findViewById<EditText>(R.id.et_content) }
    private val btnFinish by lazy { findViewById<Button>(R.id.btn_finish) }
    private var position: Int = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2_content)
        position = intent.getIntExtra("item_position", -1)
        tvContent.text = "这是从 item ${position + 1} 进入的"
        etContent.setText("我的 item ${position + 1} 已完成 √")
        btnFinish.setOnClickListener { finish() }
    }

    // 点击 finish按钮 或 back键，返回 Lab2Activity 并更新 item
    override fun finish() {
        setResultForLab2Activity()
        super.finish()
    }

    private fun setResultForLab2Activity() {
        val intent = Intent()
        intent.putExtra("item_position", position)
        intent.putExtra("edit_content", etContent.text.toString()) // 注意要 toString()
        setResult(RESULT_CODE_LAB2_CONTENT_ACTIVITY, intent)
    }
}