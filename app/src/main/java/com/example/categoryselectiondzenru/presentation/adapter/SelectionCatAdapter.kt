package com.example.categoryselectiondzenru.presentation.adapter

import android.view.*
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.categoryselectiondzenru.databinding.CategorySelectionItemBinding
import com.example.categoryselectiondzenru.model.Category
import com.example.categoryselectiondzenru.presentation.adapter.listeners.OnItemClickListener
import com.example.categoryselectiondzenru.presentation.adapter.measuring.CatManager
import com.example.categoryselectiondzenru.presentation.adapter.measuring.MeasureHelper
import kotlin.properties.Delegates

class SelectionCatAdapter(private var categoryList: List<Category>) : RecyclerView.Adapter<SelectionCatAdapter.Holder>() {

    private var ready = false
    private val measureHelper = MeasureHelper(this, categoryList.size, CatManager())
    private var recyclerView: RecyclerView? = null
    var onItemClickListener: OnItemClickListener? = null
    var measuringDone by Delegates.observable(false) { _, _, newVal ->
        if (newVal) update()
    }

    private fun update() {
        recyclerView ?: return
        recyclerView?.apply {
            visibility = View.VISIBLE
            categoryList = measureHelper.getItems() as ArrayList<Category>
            layoutManager = CustomGridLayoutManager(context, MeasureHelper.SPAN_COUNT, measureHelper.getSpans())
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView.apply {
            visibility = View.INVISIBLE
            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    measureHelper.measureBaseCell(recyclerView.width)
                    ready = true
                    notifyDataSetChanged()
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategorySelectionItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val tag = categoryList[position]

        val shouldMeasure = measureHelper.shouldMeasure()

        holder.setData(tag, shouldMeasure)


        if (shouldMeasure)
            measureHelper.measure(holder.binding, tag)
    }

    override fun getItemCount() = if (ready) categoryList.size else 0

    inner class Holder(val binding: CategorySelectionItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val customItem = binding.textViewItem

        fun setData(category: Category, shouldMeasure: Boolean) {

            customItem.text = category.name

            customItem.apply {
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            }
            if (!shouldMeasure) {
                customItem.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            }
    }
    }
}