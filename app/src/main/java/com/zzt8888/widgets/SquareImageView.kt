package com.zzt8888.widgets

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class SquareImageView : AppCompatImageView {

    private var scale = 1.0f

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        if (height in 0..width ) {
            scale = width.toFloat() / height
        }

        setMeasuredDimension(width, width)
    }

    override fun onDraw(canvas: Canvas) {

        canvas.save()
        canvas.scale(scale, scale)

        super.onDraw(canvas)
        canvas.restore()
    }
}
