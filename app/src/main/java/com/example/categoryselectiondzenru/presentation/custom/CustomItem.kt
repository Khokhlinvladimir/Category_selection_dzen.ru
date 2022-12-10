package com.example.categoryselectiondzenru.presentation.custom

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.categoryselectiondzenru.R
import kotlin.properties.Delegates

class CustomItem(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes)  {

    private lateinit var onPaint: Paint
    private lateinit var defPaint: Paint
    private var onColor by Delegates.notNull<Int>()
    private var defColor by Delegates.notNull<Int>()


    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, R.attr.customItemStyle)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, 0)

    constructor(context: Context) : this(context, null)

    init {
        initPaints()
        initAttributes(attributesSet, defStyleAttr, defStyleRes)
    }

    private fun initAttributes(attributesSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {

        val typedArray = context.obtainStyledAttributes(attributesSet, R.styleable.CustomItem, defStyleAttr, defStyleRes)

        onColor = typedArray.getColor(R.styleable.CustomItem_pressedButtonColor, OPTION1_DEFAULT_COLOR)
        defColor = typedArray.getColor(R.styleable.CustomItem_defaultButtonColor, OPTION2_DEFAULT_COLOR)


        typedArray.recycle()
    }

    private fun initPaints() {
        onPaint.apply {
            Paint(Paint.ANTI_ALIAS_FLAG)
            color = onColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)
        }

        defPaint.apply {
            Paint(Paint.ANTI_ALIAS_FLAG)
            color = defColor
            style = Paint.Style.STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)
        }
    }





    companion object {
        const val OPTION1_DEFAULT_COLOR = Color.GREEN
        const val OPTION2_DEFAULT_COLOR = Color.RED

    }
}