package com.example.categoryselectiondzenru.presentation.adapter

import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.example.categoryselectiondzenru.databinding.RowTagBinding

class MeasureHelper(
    private val adapter: TagAdapter,
    private val count: Int
) {

    companion object {
        const val SPAN_COUNT = 52
    }

    private var measuredCount = 0

    private val rowManager = TagRowManager()

    private var baseCell: Float = 0f

    fun measureBaseCell(width: Int) {
        baseCell = (width / SPAN_COUNT).toFloat()
    }

    fun shouldMeasure() = measuredCount != count

    fun getItems() = rowManager.getSortedTags()

    fun getSpans() = rowManager.getSortedSpans()

    private fun cellMeasured() {

        if (!adapter.measuringDone && !shouldMeasure())
            adapter.measuringDone = true
    }

    fun measure(holder: RowTagBinding, tag: Tag) {

        val itemView = holder.root.apply {
            layoutParams.height = 1
        }

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {


                itemView.viewTreeObserver.removeOnGlobalLayoutListener(this)


                val marginTotal =
                    (holder.chipHolder.layoutParams as ViewGroup.MarginLayoutParams).marginStart * 2


                val span = (holder.chipHolder.width + marginTotal) / baseCell

                measuredCount++

                rowManager.add(span, tag)

                cellMeasured()
            }
        }


        itemView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }
}