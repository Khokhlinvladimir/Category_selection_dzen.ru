package com.example.categoryselectiondzenru.presentation.adapter.measuring

import android.util.Log
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.viewbinding.ViewBinding
import com.example.categoryselectiondzenru.databinding.CategoryItemBinding
import com.example.categoryselectiondzenru.databinding.CategorySelectionItemBinding
import com.example.categoryselectiondzenru.presentation.adapter.CatAdapter
import com.example.categoryselectiondzenru.model.Category
import com.example.categoryselectiondzenru.presentation.adapter.SelectionCatAdapter
import javax.inject.Inject

class MeasureHelper @Inject constructor(private val adapter: Any, private val count: Int) {

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

        when(adapter){
            is CatAdapter -> {
                if (!adapter.measuringDone && !shouldMeasure()) adapter.measuringDone = true
            }
            is SelectionCatAdapter -> {
                if (!adapter.measuringDone && !shouldMeasure()) adapter.measuringDone = true
            }
        }

    }


   fun measure(holder: ViewBinding, category: Category) {

       when(holder){
           is CategoryItemBinding -> {
               holder.mCustom.apply {
                   layoutParams.height = 0

                   val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
                       override fun onGlobalLayout() {
                           viewTreeObserver.removeOnGlobalLayoutListener(this)
                           val margin =
                               (holder.mCustom.layoutParams as ViewGroup.MarginLayoutParams).marginStart
                           val span = (holder.mCustom.width + margin + 3) / baseCell
                           measuredCount++
                           rowManager.add(span, category)
                           cellMeasured()
                           Log.d("LOG", "customDebug MeasureHelper  margin $margin + span $span")
                       }
                   }
                   viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
               }
           }
           is CategorySelectionItemBinding -> {
               holder.textViewItem.apply {
                   layoutParams.height = 0

                   val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
                       override fun onGlobalLayout() {
                           viewTreeObserver.removeOnGlobalLayoutListener(this)
                           val margin =
                               (holder.textViewItem.layoutParams as ViewGroup.MarginLayoutParams).marginStart
                           val span = (holder.textViewItem.width + margin + 3) / baseCell
                           measuredCount++
                           rowManager.add(span, category)
                           cellMeasured()
                       }
                   }
                   viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
               }
           }
       }




   }

    companion object {
        const val SPAN_COUNT = 100
    }
}