package com.example.categoryselectiondzenru.presentation.adapter.measuring

import com.example.categoryselectiondzenru.presentation.adapter.data.Tag
import com.example.categoryselectiondzenru.presentation.adapter.measuring.MeasureHelper
import kotlin.math.ceil

class TagRow {


    var freeSpans = MeasureHelper.SPAN_COUNT

    val tagList = mutableListOf<Tag>()

    val spanList = mutableListOf<Int>()

    fun addTag(spanRequired: Float, tag: Tag) : Boolean {

        if (spanRequired < freeSpans)
            if (tagList.add(tag)) {

                val spanRequiredInt = ceil(spanRequired).toInt()

                spanList.add(spanRequiredInt)

                freeSpans -= spanRequiredInt

                return true
            }

        return false
    }
}