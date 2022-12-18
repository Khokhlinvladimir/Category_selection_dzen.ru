package com.example.categoryselectiondzenru.presentation.adapter.measuring

import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.example.categoryselectiondzenru.databinding.CategoryItemBinding
import com.example.categoryselectiondzenru.presentation.adapter.TagAdapter
import com.example.categoryselectiondzenru.presentation.adapter.data.Tag

class MeasureHelper(private val adapter: TagAdapter, private val count: Int) {

    companion object {
        const val SPAN_COUNT = 100
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


   fun measure(holder: CategoryItemBinding, tag: Tag) {

        val itemView = holder.root.apply {
            layoutParams.height = 0
        }

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                itemView.viewTreeObserver.removeOnGlobalLayoutListener(this)


                val margin = (holder.mCustom.layoutParams as ViewGroup.MarginLayoutParams).marginStart

              //  Log.d("LOG", "marginTotal $marginTotal")
                val span = (holder.mCustom.width +margin + 3) / baseCell

                measuredCount++

                rowManager.add(span, tag)

                cellMeasured()
            }
        }


        itemView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }
}