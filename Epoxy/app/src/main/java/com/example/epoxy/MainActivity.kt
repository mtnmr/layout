package com.example.epoxy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val itemList = listOf<String>(
        "Item 1", "Item 2", "Item 3", "Item 4"
    )

    private val itemsForCustom = listOf(
        "Custom 1", "Custom 2", "Custom 3", "Custom 4"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val epoxyController = EpoxyController(this)

        findViewById<RecyclerView>(R.id.recycler_view).adapter = epoxyController.adapter

        epoxyController.setData(itemList,  itemsForCustom)
    }
}