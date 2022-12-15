package com.example.categoryselectiondzenru.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.categoryselectiondzenru.R
import com.example.categoryselectiondzenru.databinding.RowTagBinding

import com.example.categoryselectiondzenru.presentation.adapter.listeners.OnTaggableClickListener
import kotlin.properties.Delegates

class TagAdapter(private var tagList: List<Tag>, private val selection: Selection
) : RecyclerView.Adapter<TagAdapter.Holder>() {

    private val measureHelper = MeasureHelper(this, tagList.size)

    private var recyclerView: RecyclerView? = null

    private var ready = false

    var onTaggableClickListener: OnTaggableClickListener? = null

    var measuringDone by Delegates.observable(false) { _, _, newVal ->
        if (newVal)
            update()
    }


    private fun update() {

        recyclerView ?: return

        recyclerView?.apply {

            visibility = View.VISIBLE

            tagList = measureHelper.getItems() as ArrayList<Tag>

            layoutManager = CustomGridLayoutManager(context, MeasureHelper.SPAN_COUNT, measureHelper.getSpans())
        }
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView.apply {

            visibility = View.INVISIBLE

            viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
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
        val binding = RowTagBinding.inflate(inflater, parent, false)

        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {

        val tag = tagList[position]

        val shouldMeasure = measureHelper.shouldMeasure()

        holder.setData(tag, shouldMeasure)

        holder.setClickListener(tag, onTaggableClickListener)


        if (shouldMeasure)
            measureHelper.measure(holder.binding, tag)
    }


    override fun getItemCount() = if (ready) tagList.size else 0






    inner class Holder(val binding: RowTagBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(tag: Tag, shouldMeasure: Boolean) {

            val myItemBinding = binding.myItem

            myItemBinding.text.text = "${tag.title} "



            binding.chipHolder.apply {
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            }

            if (!shouldMeasure)
                binding.chipHolder.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT

            binding.chipHolder.setOnClickListener {

                if (selection == Selection.MULTI_SELECTION) {
                    tag.checked = !tag.checked
                    notifyItemChanged(adapterPosition)

                }

                else if (selection == Selection.SINGLE_SELECTION) {
                    tagList.forEach { it.checked = false }
                    tag.checked = true

                    notifyDataSetChanged()
                }
            }

            if (tag.checked) {
                myItemBinding.cardHolder.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.custom_selected_card_shape, null)

                myItemBinding.text.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))

                myItemBinding.checkedImgv.visibility = View.VISIBLE

            } else {

                myItemBinding.cardHolder.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.custom_un_selected_card_shape, null)

                myItemBinding.text.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                myItemBinding.checkedImgv.visibility = View.INVISIBLE
            }

        }

        fun setClickListener(tag: Tag, onTaggableClickListener: OnTaggableClickListener?) {
            onTaggableClickListener?.let {
                binding.chipHolder.setOnClickListener {
                    onTaggableClickListener.onTaggableClick(tag)
                }
            }
        }
    }


}