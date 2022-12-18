package com.example.categoryselectiondzenru.presentation.custom

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
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
    private var corner: Float = 38f
    private var valuePlus: Float = 25f
    private var radiusButton: Float = 0f
    private var alphaAnimation: Int = 255
    private var counter: Int = 0
    private var centerX: Float = 0f
    private var centerY: Float = 0f


    private var animatorPlus: ValueAnimator? = null
    private var animatorButton: ValueAnimator? = null
    private var animatorAlpha: ValueAnimator? = null
    var text = "Кинотеатр"

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

        centerX = rectWidth  - 50f
        centerY = rectHeight / 2f
    }



    override fun draw(canvas: Canvas) {
        super.draw(canvas)



        drawButton(canvas)

        drawLine(canvas)

        drawText(canvas)

        // drawPlus(canvas)

         drawCheck(canvas)

        invalidate()
    }

    private fun drawButton(canvas: Canvas){

        val path = Path().apply {
            addRoundRect(rect, corner, corner, Path.Direction.CW)
        }

        canvas.drawPath(path, defPaint)

        canvas.clipPath(path)

        canvas.drawCircle(centerX, centerY, radiusButton, onPaint)

    }



    private fun drawLine(canvas: Canvas){
        linePaint.alpha = alphaAnimation
        canvas.drawLine(rectWidth - 105f, 25f, rectWidth - 105f, rectHeight -25f, linePaint)
    }


    private fun drawText(canvas: Canvas){
        canvas.drawText(text, startX + 30f, rectHeight/2f+14f, textPaint)
    }


    private fun drawPlus(canvas: Canvas){
        checkPaint.alpha = alphaAnimation
        canvas.drawLine(centerX, centerY - valuePlus, centerX, centerY + valuePlus , checkPaint)

        canvas.drawLine(centerX - valuePlus, centerY, centerX + valuePlus, centerY, checkPaint)

    }

    private fun plusAnimation(start: Int, end: Int) {
        animatorPlus?.cancel()
        animatorPlus = ValueAnimator.ofInt(start, end).apply {
            duration = 200
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                valuePlus = (valueAnimator.animatedValue as Int).toFloat()
            }
        }
        animatorPlus?.start()
    }

    private fun buttonAnimation(start: Int, end: Int) {
        animatorButton?.cancel()
        animatorButton = ValueAnimator.ofInt(start, end).apply {
            startDelay = 200
            duration = 200
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                radiusButton = (valueAnimator.animatedValue as Int).toFloat()
                invalidate()
            }
        }
        animatorButton?.start()
    }

    private fun alphaAnimation(start: Int, end: Int, value: Long) {
        animatorAlpha?.cancel()
        animatorAlpha = ValueAnimator.ofInt(start, end).apply {
            startDelay = value
            duration = 10
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                alphaAnimation = valueAnimator.animatedValue as Int
                invalidate()
            }
        }
        animatorAlpha?.start()
    }


    private fun drawCheck(canvas: Canvas){

        val bottomX = rectWidth - 70f
        val bottomY = rectHeight -40f

        val startX1 = bottomX - 16f
        val startY1 = bottomY - 16f


        canvas.drawLine(startX1, startY1, bottomX, bottomY , checkPaint)

        val startX2 = bottomX + 32f
        val startY2 = bottomY - 32f


        canvas.drawLine(startX2, startY2, bottomX, bottomY, checkPaint)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN -> return true

            MotionEvent.ACTION_UP -> {

                counter += 1

                if (counter %2==0){
                    plusAnimation(0, 25)
                    alphaAnimation(0, 255, 0)
                    buttonAnimation(rectWidth, 0)
                }
                else {
                    plusAnimation(25, 0)
                    buttonAnimation(0, rectWidth)
                    alphaAnimation(255, 0, 200)

                }

                invalidate()

            }
        }
        return super.callOnClick()
    }






    companion object {
        const val OPTION1_DEFAULT_COLOR = Color.GREEN
        const val OPTION2_DEFAULT_COLOR = Color.RED
    }
}