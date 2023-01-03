package com.example.epoxy

import android.content.Context
import com.airbnb.epoxy.Typed2EpoxyController

class EpoxyController(context: Context) : Typed2EpoxyController<List<String>,List<String>>() {

    private val contextForCustom = context

    override fun buildModels(items: List<String>, itemsForCustom:List<String>) {
        items.forEach { item ->
            listItem {
                id("Content")
                title(item)
            }
        }

//        itemsForCustom.forEach { item ->
//            EpoxyCustomView(contextForCustom)
//                .setTitle(item)
//        }
    }
}

