package com.example.categoryselectiondzenru.presentation.adapter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class CustomGridLayoutManager(context: Context, spanCount: Int, spanList: MutableList<Int>) :
    GridLayoutManager(context, spanCount) {

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int) = spanList[position]
        }
    }
}