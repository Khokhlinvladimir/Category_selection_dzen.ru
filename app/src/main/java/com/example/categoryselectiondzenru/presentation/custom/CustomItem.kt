package com.example.categoryselectiondzenru.presentation.custom

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.properties.Delegates

class CustomItem(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes)  {

    private lateinit var onPaint: Paint
    private lateinit var offPaint: Paint
    private var onColor by Delegates.notNull<Int>()
    private var offColor by Delegates.notNull<Int>()


    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, 0)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, 0)

    constructor(context: Context) : this(context, null)

    init {
        initPaints()
    }



    private fun initPaints() {
        onPaint.apply {
            Paint(Paint.ANTI_ALIAS_FLAG)
            color = onColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)
        }

        offPaint.apply {
            Paint(Paint.ANTI_ALIAS_FLAG)
            color = offColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)
        }
    }



 /*   private fun initAttributes(attributesSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {

        val typedArray = context.obtainStyledAttributes(attributesSet, R.styleable.CustomItem, defStyleAttr, defStyleRes)

        // parsing XML attributes
        option1Color = typedArray.getColor(R.styleable.CustomItem_option1Color, OPTION1_DEFAULT_COLOR)
        option2Color = typedArray.getColor(R.styleable.CustomItem_option2Color, OPTION2_DEFAULT_COLOR)


        typedArray.recycle()
    }*/

    companion object {
        const val OPTION1_DEFAULT_COLOR = Color.GREEN
        const val OPTION2_DEFAULT_COLOR = Color.RED

    }
}