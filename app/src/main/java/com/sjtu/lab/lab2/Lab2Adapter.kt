package com.sjtu.lab.lab2

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sjtu.lab.R
import com.sjtu.lab.lab2.Lab2Activity.Companion.REQUEST_CODE_LAB2_ACTIVITY

class Lab2Adapter(
    private val mActivity: Activity,
    private val list: MutableList<String>
) : RecyclerView.Adapter<Lab2Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_lab2_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItem.text = list[position]
        holder.tvItem.setOnClickListener { startContentActivity(position) }
    }

    private fun startContentActivity(position: Int) {
        val intent = Intent(mActivity, Lab2ContentActivity::class.java)
        intent.putExtra("item_position", position)
        mActivity.startActivityForResult(intent, REQUEST_CODE_LAB2_ACTIVITY)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}