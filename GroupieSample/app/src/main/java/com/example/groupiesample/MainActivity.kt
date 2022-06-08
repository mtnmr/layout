package com.example.groupiesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.groupiesample.databinding.ActivityMainBinding
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = GroupieAdapter()
        binding.recyclerview.adapter = adapter

        adapter.add(ListItem("sample"))

        val listItems = listOf(ListItem("sample2"), ListItem("sample3"))
//        adapter.update(listItems)
        val section = Section()
        section.addAll(listItems)
        adapter.add(section)
    }

}