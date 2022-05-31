package com.example.recyclerviewsample


import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsample.ItemListAdapter.*
import com.example.recyclerviewsample.databinding.ListItemBinding

class ItemListAdapter : ListAdapter<Item, ItemViewHolder>(diffCallback) {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)            // Stable ID利用の宣言
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        tracker?.let {   // 選択の有無でstate_activatedを変える
            holder.itemView.isActivated = it.isSelected(getItemId(position))
        }
    }

    class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
          fun bind(item:Item){
              binding.itemText.text =  item.item.toString()
          }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).item.toLong()
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.item == newItem.item
            }

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }
        }
    }

}

class SampleItemDetailsLookup(private val recyclerView: RecyclerView)
    : ItemDetailsLookup<Long>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val _view = recyclerView.findChildViewUnder(e.x, e.y)
        return _view?.let {
            val _holder = recyclerView.getChildViewHolder(it)
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = _holder.absoluteAdapterPosition
                override fun getSelectionKey(): Long = _holder.itemId
            }
        }
    }
}

