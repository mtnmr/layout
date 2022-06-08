package com.example.groupiesample

import com.example.groupiesample.databinding.ListItemBinding
import com.xwray.groupie.databinding.BindableItem

class ListItem(private val text:String) : BindableItem<ListItemBinding>() {
    override fun bind(viewBinding: ListItemBinding, position: Int) {
        viewBinding.text = text
    }

    override fun getLayout(): Int  = R.layout.list_item
}