package com.example.categoryselectiondzenru.presentation.adapter

class TagRowManager {


    private val rowList = mutableListOf<TagRow>()
        .apply {

            add(TagRow())
        }


    fun add(spanRequired: Float, tag: Tag) {

        for (i in 0..rowList.size) {

            val tagRow = rowList[i]


            if (tagRow.addTag(spanRequired, tag))
                break


            if (i == rowList.lastIndex)
                rowList.add(TagRow())
        }
    }

    fun getSortedSpans() =
        mutableListOf<Int>().apply {
            rowList.forEach {
                addAll(it.spanList)
            }
        }

    fun getSortedTags() =
        mutableListOf<Tag>().apply {
            rowList.forEach {
                addAll(it.tagList)
            }
        }
}