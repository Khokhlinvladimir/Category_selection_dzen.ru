package com.example.categoryselectiondzenru.presentation.custom

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.DecelerateInterpolator
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
    private lateinit var checkPaint: Paint
    private var onColor = ContextCompat.getColor(context, R.color.button_on_color)
    private var defColor =  ContextCompat.getColor(context, R.color.button_def_color)
    private var textColor =  ContextCompat.getColor(context, R.color.white)
    private var lineColor =  ContextCompat.getColor(context, R.color.text_header_color)
    private val rect: RectF = RectF(0f, 0f, 0f, 0f)
    private var rectWidth: Int = 180
    private val rectHeight: Int = 110
    private var startX: Float = 0f
    private var startY: Float = 0f
    private var textSize: Int = 130
    private var corner: Float = 32F
    private var valuePlus: Float = 25f
    var text = "Кинотеатр"


    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, R.attr.customItemStyle)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, R.attr.customItemStyle)

    constructor(context: Context) : this(context, null)

    init {
        initPaints()
        initAttributes(attributesSet, defStyleAttr, defStyleRes)
        plusAnimate ()
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
            textSize = 47f
        }

        linePaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.apply {
            color = lineColor
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
        }

        checkPaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        checkPaint.apply {
            color = textColor
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.5f, resources.displayMetrics)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        textSize = ((text.length) * 32.5).toInt()

        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val desiredWidth = max(minWidth,  rectWidth + (textSize * 0.85).toInt() + paddingLeft + paddingRight)
        val desiredHeight = max(minHeight,  rectHeight + paddingTop + paddingBottom)

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec))

    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        rect.set(startX, startY, rectWidth.toFloat() + (textSize * 0.85f),
            rectHeight.toFloat())

        rectWidth += (textSize * 0.85).toInt()
    }



    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        drawButton(canvas)

        drawLine(canvas)

        drawText(canvas)

        //   drawPlus(canvas)

          drawCheck(canvas)

        invalidate()
    }

    private fun drawButton(canvas: Canvas){
        canvas.drawRoundRect(rect, corner, corner, defPaint)
    }

    private fun drawLine(canvas: Canvas){
        canvas.drawLine(rectWidth - 105f, 25f, rectWidth - 105f, rectHeight -25f, linePaint)
    }


    private fun drawText(canvas: Canvas){
        canvas.drawText(text, startX + 30f, rectHeight/2f+14f, textPaint)
    }


    private fun drawPlus(canvas: Canvas){


        val verticalX = rectWidth  - 50f
        val centerY = rectHeight / 2f

        canvas.drawLine(verticalX, centerY - valuePlus, verticalX, centerY + valuePlus , checkPaint)

        canvas.drawLine(verticalX - valuePlus, centerY, verticalX + valuePlus, centerY, checkPaint)
    }

    private fun plusAnimate (){
        val animator = ObjectAnimator.ofFloat(this, "valuePlus", 0f, 25f)
        animator.apply {
            duration = 5000
            DecelerateInterpolator()
            start()
        }
    }


    private fun drawCheck(canvas: Canvas){


        val bottomX = rectWidth - 70f
        val bottomY = rectHeight -40f

        val startVerticalX = rectWidth - 85f
        val startVerticalY = rectHeight -55f


        canvas.drawLine(startVerticalX, startVerticalY, bottomX, bottomY , checkPaint)

        val startHorizontalX = rectWidth - 35f
        val startHorizontalY = rectHeight -75f


        canvas.drawLine(startHorizontalX, startHorizontalY, bottomX, bottomY, checkPaint)
    }



    private fun clickListener(){

        this.setOnClickListener {
            plusAnimate ()
        }

    }



    companion object {
        const val OPTION1_DEFAULT_COLOR = Color.GREEN
        const val OPTION2_DEFAULT_COLOR = Color.RED
    }
}