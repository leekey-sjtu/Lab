package com.sjtu.lab.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sjtu.lab.R
import com.sjtu.lab.lab2.Lab2ContentActivity.Companion.RESULT_CODE_LAB2_CONTENT_ACTIVITY

class Lab2Activity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_LAB2_ACTIVITY = 2000
    }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private var itemList = mutableListOf<String>()
    private var lab2Adapter = Lab2Adapter(this, itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab2)
        for (i in 1..100) { itemList.add("这是 item $i") }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lab2Adapter
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_LAB2_ACTIVITY) {
            if (resultCode == RESULT_CODE_LAB2_CONTENT_ACTIVITY) {
                if (data != null) {
                    val position = data.getIntExtra("item_position", -1)
                    val content = data.getStringExtra("edit_content")
                    itemList.removeAt(position)
                    content?.let { itemList.add(position, it) }
                    lab2Adapter.notifyItemChanged(position) // 只刷新单个item
                }
            }
        }
    }
}