package com.sjtu.lab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sjtu.lab.lab1.Lab1Activity
import com.sjtu.lab.lab2.Lab2Activity
import com.sjtu.lab.lab3.Lab3Activity
import com.sjtu.lab.lab4.Lab4Activity
import com.sjtu.lab.lab5.Lab5Activity
import com.sjtu.lab.lab6.Lab6Activity
import com.sjtu.lab.lab7.Lab7Activity
import com.sjtu.lab.lab8.Lab8Activity

class MainActivity : AppCompatActivity() {

    private val btnLab1 by lazy { findViewById<Button>(R.id.btn_lab_1) }
    private val btnLab2 by lazy { findViewById<Button>(R.id.btn_lab_2) }
    private val btnLab3 by lazy { findViewById<Button>(R.id.btn_lab_3) }
    private val btnLab4 by lazy { findViewById<Button>(R.id.btn_lab_4) }
    private val btnLab5 by lazy { findViewById<Button>(R.id.btn_lab_5) }
    private val btnLab6 by lazy { findViewById<Button>(R.id.btn_lab_6) }
    private val btnLab7 by lazy { findViewById<Button>(R.id.btn_lab_7) }
    private val btnLab8 by lazy { findViewById<Button>(R.id.btn_lab_8) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        btnLab1.setOnClickListener { startActivity(Intent(this, Lab1Activity::class.java)) }
        btnLab2.setOnClickListener { startActivity(Intent(this, Lab2Activity::class.java)) }
        btnLab3.setOnClickListener { startActivity(Intent(this, Lab3Activity::class.java)) }
        btnLab4.setOnClickListener { startActivity(Intent(this, Lab4Activity::class.java)) }
        btnLab5.setOnClickListener { startActivity(Intent(this, Lab5Activity::class.java)) }
        btnLab6.setOnClickListener { startActivity(Intent(this, Lab6Activity::class.java)) }
        btnLab7.setOnClickListener { startActivity(Intent(this, Lab7Activity::class.java)) }
        btnLab8.setOnClickListener { startActivity(Intent(this, Lab8Activity::class.java)) }
    }
}