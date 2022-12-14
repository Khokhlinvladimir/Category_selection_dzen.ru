package com.example.categoryselectiondzenru.presentation.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.math.max
import kotlin.math.min
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
    private var onColor = ContextCompat.getColor(context, R.color.button_on_color)
    private var defColor =  ContextCompat.getColor(context, R.color.button_def_color)
    private val rect: RectF = RectF(0f, 0f, 0f, 0f)
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var radius: Float = 0f


    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, R.attr.customItemStyle)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, R.attr.customItemStyle)

    constructor(context: Context) : this(context, null)

    init {
        initPaints()
        initAttributes(attributesSet, defStyleAttr, defStyleRes)

        if (isInEditMode) {
            centerX= (width / 4).toFloat()
            centerY= (height / 4).toFloat()
            radius = 10f
        }
    }

    private fun initAttributes(attributesSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {

        val typedArray = context.obtainStyledAttributes(attributesSet, R.styleable.CustomItem, defStyleAttr, defStyleRes)

        onColor = typedArray.getColor(R.styleable.CustomItem_pressedButtonColor, OPTION1_DEFAULT_COLOR)
        defColor = typedArray.getColor(R.styleable.CustomItem_defaultButtonColor, OPTION2_DEFAULT_COLOR)


        typedArray.recycle()
    }

    private fun initPaints() {
        onPaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        onPaint.apply {
            color = onColor
            style = Paint.Style.FILL
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)
        }

        defPaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        defPaint.apply {
            color = defColor
            style = Paint.Style.FILL
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val desiredCellSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f,
            resources.displayMetrics).toInt()

        val desiredWith = max(minWidth,  desiredCellSizeInPixels + paddingLeft + paddingRight)
        val desiredHeight = max(minHeight,  desiredCellSizeInPixels + paddingTop + paddingBottom)

        setMeasuredDimension(
            resolveSize(desiredWith, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 4).toFloat()
        centerY = (h / 4).toFloat()

        radius = if (w > h) { h / 10f } else { w / 10f }


        rect.set(centerX - radius, centerY, centerX + radius,
            centerY + radius)
    }



    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawRoundRect(rect, 32F, 32F, defPaint)

    }



    companion object {
        const val OPTION1_DEFAULT_COLOR = Color.GREEN
        const val OPTION2_DEFAULT_COLOR = Color.RED

    }
}