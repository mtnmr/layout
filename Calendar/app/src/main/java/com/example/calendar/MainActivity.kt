package com.example.calendar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel : CalendarViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val dateList = viewModel.getDays()
        val adapter = ItemAdapter(dateList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 7,  RecyclerView.VERTICAL, false)

        val title = findViewById<TextView>(R.id.titleText)
        val prev = findViewById<Button>(R.id.prevButton)
        val next = findViewById<Button>(R.id.nextButton)

        title.text = viewModel.getTitle()

        prev.setOnClickListener {
            adapter.dateList = viewModel.prevMonth()
            adapter.notifyDataSetChanged()
            title.text = viewModel.getTitle()
        }

        next.setOnClickListener {
            adapter.dateList = viewModel.nextMonth()
            adapter.notifyDataSetChanged()
            title.text = viewModel.getTitle()
        }
    }
}