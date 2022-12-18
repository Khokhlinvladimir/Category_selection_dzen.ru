package com.example.categoryselectiondzenru.presentation.adapter.measuring

import com.example.categoryselectiondzenru.model.Category
import kotlin.math.ceil

class CatManager {


    private val catList = mutableListOf<DataCat>()
        .apply {
            add(DataCat())
        }

    fun add(spanRequired: Float, category: Category) {

        for (i in 0..catList.size) {

            val tagRow = catList[i]

            if (tagRow.addCat(spanRequired, category))
                break

            if (i == catList.lastIndex)
                catList.add(DataCat())
        }
    }

    fun getSortedSpans() =
        mutableListOf<Int>().apply {
            catList.forEach {
                addAll(it.spanList)
            }
        }

    fun getSortedTags() =
        mutableListOf<Category>().apply {
            catList.forEach {
                addAll(it.categoryList)
            }
        }


    inner class DataCat {

        private var freeSpans = MeasureHelper.SPAN_COUNT

        val categoryList = mutableListOf<Category>()

        val spanList = mutableListOf<Int>()

        fun addCat(spanRequired: Float, category: Category): Boolean {


            if (spanRequired < freeSpans)
                if (categoryList.add(category)) {


                    val spanRequiredInt = ceil(spanRequired).toInt()


                    spanList.add(spanRequiredInt)

                    freeSpans -= spanRequiredInt

                    return true
                }

            return false
        }
    }
}