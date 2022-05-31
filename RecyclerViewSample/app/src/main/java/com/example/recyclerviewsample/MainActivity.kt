package com.example.recyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tracker: SelectionTracker<Long>
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val list = mutableListOf<Item>()
//        for (i in 1..20) {
//            val current = Item(item = i)
//            list.add(current)
//        }
//
//        val view = findViewById<RecyclerView>(R.id.recycler_view)
//        val adapter = ItemListAdapter()
//        view.adapter = adapter
//        view.addItemDecoration(
//            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        )
//        adapter.submitList(list)
//
//        tracker = SelectionTracker.Builder(
//            "ListTracker",
//            view,
//            StableIdKeyProvider(view),
//            SampleItemDetailsLookup(view),
//            StorageStrategy.createLongStorage()
//        )
//            .withSelectionPredicate(SelectionPredicates.createSelectAnything())
//            .build()
//
//        adapter.tracker = tracker

//        tracker.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
//            override fun onSelectionChanged() {
//                if (tracker.selection.size() >= 1 && actionMode == null) {
//                    actionMode = requireActivity().startActionMode(object : ActionMode.Callback {
//                        override fun onActionItemClicked(
//                            mode: ActionMode?,
//                            item: MenuItem?
//                        ): Boolean {
//                            return when (item?.itemId) {
//                                R.id.delete -> {
//                                    //削除処理
//                                    true
//                                }
//                                else -> {
//                                    true
//                                }
//                            }
//                        }
//
//                        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
//                            mode?.menuInflater?.inflate(R.menu.menu, menu)
//                            return true
//                        }
//
//                        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
//                            return false
//                        }
//
//                        override fun onDestroyActionMode(mode: ActionMode?) {
//                            actionMode = null
//                            if (tracker.hasSelection()) {
//                                tracker.clearSelection()
//                                adapter.notifyDataSetChanged()
//                            }
//                        }
//                    })
//                } else if (!tracker.hasSelection()) {
//                    actionMode?.finish()
//                }
//            }
//
//            override fun onSelectionRestored() {
//                super.onSelectionRestored()
//
//                // 復元後アクションモードの起動を判断するため
//                this.onSelectionChanged()
//            }
//        })

    }
}
