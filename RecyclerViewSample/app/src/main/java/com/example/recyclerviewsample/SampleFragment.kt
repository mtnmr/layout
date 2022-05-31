package com.example.recyclerviewsample

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsample.databinding.FragmentSampleBinding

class SampleFragment : Fragment() {

    private var _binding: FragmentSampleBinding? = null
    private val binding get() = _binding!!

    private lateinit var tracker: SelectionTracker<Long>
    private var actionMode: ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf<Item>()
        for (i in 1..20) {
            val current = Item(item = i)
            list.add(current)
        }

        val view = binding.recyclerView
        val adapter = ItemListAdapter()
        view.adapter = adapter
        view.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        adapter.submitList(list)

        tracker = SelectionTracker.Builder(
            "ListTracker",
            view,
            StableIdKeyProvider(view),
            SampleItemDetailsLookup(view),
            StorageStrategy.createLongStorage()
        )
            .withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build()

        adapter.tracker = tracker

        tracker.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {
                if (tracker.selection.size() >= 1 && actionMode == null) {
                    actionMode = requireActivity().startActionMode(object : ActionMode.Callback {
                        override fun onActionItemClicked(
                            mode: ActionMode?,
                            item: MenuItem?
                        ): Boolean {
                            return when (item?.itemId) {
                                R.id.delete -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "delete button",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val items = tracker.selection
                                    items.forEach { it ->
                                        Log.d("Selection check", it.toString())
                                    }
                                    mode?.finish()
                                    true
                                }
                                else -> {
                                    true
                                }
                            }
                        }

                        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                            mode?.menuInflater?.inflate(R.menu.menu, menu)
                            return true
                        }

                        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                            return false
                        }

                        override fun onDestroyActionMode(mode: ActionMode?) {
                            actionMode = null
                            if (tracker.hasSelection()) {
                                tracker.clearSelection()
                                adapter.notifyDataSetChanged()
                            }
                        }
                    })
                } else if (!tracker.hasSelection()) {
                    actionMode?.finish()
                }
            }

            override fun onSelectionRestored() {
                super.onSelectionRestored()

                // 復元後アクションモードの起動を判断するため
                this.onSelectionChanged()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}