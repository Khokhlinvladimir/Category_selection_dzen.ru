package com.example.categoryselectiondzenru.presentation.adapter.measuring

import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.example.categoryselectiondzenru.databinding.CategoryItemBinding
import com.example.categoryselectiondzenru.presentation.adapter.TagAdapter
import com.example.categoryselectiondzenru.model.Category

class MeasureHelper(private val adapter: TagAdapter, private val count: Int) {

    companion object {
        const val SPAN_COUNT = 100
    }

    private var measuredCount = 0

    private val rowManager = CatManager()

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


   fun measure(holder: CategoryItemBinding, category: Category) {

        holder.root.apply {
            layoutParams.height = 0

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

              viewTreeObserver.removeOnGlobalLayoutListener(this)

                val margin = (holder.mCustom.layoutParams as ViewGroup.MarginLayoutParams).marginStart

                val span = (holder.mCustom.width +margin + 3) / baseCell

                measuredCount++

                rowManager.add(span, category)

                cellMeasured()


            }
        }
            viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
   }

    }
}