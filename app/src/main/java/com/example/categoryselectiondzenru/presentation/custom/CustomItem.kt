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
import com.example.categoryselectiondzenru.R
import kotlin.math.max

class CustomItem(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes)  {

    private lateinit var onPaint: Paint
    private lateinit var defPaint: Paint
    private lateinit var textPaint: Paint
    private lateinit var linePaint: Paint
    private lateinit var plusPaint: Paint
    private var onColor = ContextCompat.getColor(context, R.color.button_on_color)
    private var defColor =  ContextCompat.getColor(context, R.color.button_def_color)
    private var textColor =  ContextCompat.getColor(context, R.color.white)
    private var lineColor =  ContextCompat.getColor(context, R.color.text_header_color)
    private val rect: RectF = RectF(0f, 0f, 0f, 0f)
    private val rectWidth: Int = 200
    private val rectHeight: Int = 120
    private var startX: Float = 0f
    private var startY: Float = 0f
    private var textSize: Int = 150
    private var corner: Float = 32F


    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, R.attr.customItemStyle)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, R.attr.customItemStyle)

    constructor(context: Context) : this(context, null)

    init {
        initPaints()
        initAttributes(attributesSet, defStyleAttr, defStyleRes)

      /*  if (isInEditMode) {
            startX= 0f
            startY= 0f
        }*/
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

       textPaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.apply {
            color = textColor
            style = Paint.Style.FILL
            textSize = 50f
        }

        linePaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.apply {
            color = lineColor
            style = Paint.Style.FILL
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
        }

        plusPaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        plusPaint.apply {
            color = textColor
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.5f, resources.displayMetrics)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val desiredWidth = max(minWidth,  rectWidth + textSize + paddingLeft + paddingRight)
        val desiredHeight = max(minHeight,  rectHeight + paddingTop + paddingBottom)

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec))
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)




        rect.set(startX, startY, rectWidth.toFloat() + textSize,
            rectHeight.toFloat())
    }



    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        drawButton(canvas)

        drawLine(canvas)

        drawText(canvas)

      //  drawPlus(canvas)

        drawCheck(canvas)

    }

    private fun drawButton(canvas: Canvas){
        canvas.drawRoundRect(rect, corner, corner, defPaint)
    }

    private fun drawLine(canvas: Canvas){
        canvas.drawLine(textSize + 60f, 25f, textSize + 60f, rectHeight -25f, linePaint)
    }


    private fun drawText(canvas: Canvas){
        canvas.drawText("Кино", startX + 30f, rectHeight/2f+18f, textPaint)
    }

    private fun drawPlus(canvas: Canvas){

        val verticalX = textSize + rectWidth - 70f
        val startVerticalY = 35f
        val stopVerticalY = rectHeight -35f

        canvas.drawLine(verticalX, startVerticalY, verticalX, stopVerticalY , plusPaint)
        val verticalY = rectHeight/2f
        val startHorizontalX = verticalX - (rectHeight-70)/2f
        val stopHorizontalX = verticalX + (rectHeight-70)/2f

        canvas.drawLine(startHorizontalX, verticalY, stopHorizontalX, verticalY, plusPaint)
    }

    private fun drawCheck(canvas: Canvas){

        val startVerticalX = textSize + rectWidth - 90f
        val stopVerticalX = textSize + rectWidth - 70f

        val startVerticalY = rectHeight -55f
        val stopVerticalY = rectHeight -40f

        canvas.drawLine(startVerticalX, startVerticalY, stopVerticalX, stopVerticalY , plusPaint)

        val startHorizontalX = textSize + rectWidth - 35f
        val stopHorizontalX = textSize + rectWidth - 70f

        val startHorizontalY = rectHeight -80f
        val stopHorizontalY = rectHeight -40f

        canvas.drawLine(startHorizontalX, startHorizontalY, stopHorizontalX, stopHorizontalY, plusPaint)
    }





    companion object {
        const val OPTION1_DEFAULT_COLOR = Color.GREEN
        const val OPTION2_DEFAULT_COLOR = Color.RED
        const val DESIRED_CELL_SIZE = 250f
    }
}