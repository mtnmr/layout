package com.example.customimageviewsample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

//自分で描写するView、上手くピンチイン・アウトできた
//https://qiita.com/vram/items/2496a8cd51cc32ded877
/*
class CustomView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    var paint: Paint = Paint()
    var x1 : Float = 0F
    var y1 : Float = 0F
    var x0 : Float = 0F
    var y0 : Float = 0F
    var scale : Float = 1F
    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.argb(255, 0, 127, 0))
        x0  = (width / 2F)
        y0  = (height /2F)
        paint.strokeWidth = 15F
        paint.color = Color.argb(255, 0, 255, 120)
        canvas.drawLine(xx(0F),yy(-100F),xx(100F),yy(100F), paint)
        canvas.drawLine(xx(-100F),yy(100F), xx(100F), yy(100F), paint)
        canvas.drawLine(xx(-100F),yy(100F),xx(0F),yy(-100F), paint)
        paint.color = Color.argb(255, 255, 255, 68)
        paint.strokeWidth = 30F
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(xx(0F),yy(0F) , 10F, paint)
    }
    fun xx(x : Float) : Float{
        return x0 + x * scale + x1
    }
    fun yy(y : Float) : Float{
        return y0 + y * scale + y1
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        mScaleDetector.onTouchEvent(ev)
        mGestureDetector.onTouchEvent(ev)
        return true
    }

    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scale *= detector.scaleFactor
            invalidate()
            return true
        }
    }

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            x1 -= distanceX
            y1 -= distanceY
            invalidate()
            return true
        }
    }
    private val mScaleDetector = ScaleGestureDetector(context, scaleListener)
    private val mGestureDetector = GestureDetector(context,gestureListener)
}

 */


//できなかった
//デバッグでscaleが1.0から変わってなかった
//https://tech.pjin.jp/blog/2015/11/24/androidimageview%e3%82%92%e3%82%ab%e3%82%b9%e3%82%bf%e3%83%9e%e3%82%a4%e3%82%ba%e3%81%99%e3%82%8b%e3%80%90%e5%89%8d%e7%b7%a8-%e3%83%94%e3%83%b3%e3%83%81%e3%82%a4%e3%83%b3%e3%83%bb%e3%83%94%e3%83%b3/
class CustomView: AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)



    private var bitmapMatrix = Matrix()
    private val SCALE_MAX = 3.0f
    private val SCALE_MIN = 0.5f
    private val PINCH_SENSITIVITY = 5.0f

    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        private var focusX : Float ?= null
        private var focusY : Float ?= null
        var scaleFactor = 1.0f

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            focusX = detector?.focusX
            focusY = detector?.focusY
            return super.onScaleBegin(detector)
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {

            val previousScale: Float = getMatrixValue(Matrix.MSCALE_Y)

            scaleFactor = if (detector.scaleFactor >= 1.0f) {
                1 + (detector.scaleFactor - 1) / (previousScale * PINCH_SENSITIVITY)
            } else {
                1 - (1 - detector.scaleFactor) / (previousScale * PINCH_SENSITIVITY)
            }

            val scale = scaleFactor * previousScale

            if (scale < SCALE_MIN) {
                return false
            }

            if (scale > SCALE_MAX) {
                return false
            }

            matrix.postScale(scaleFactor, scaleFactor, focusX!!, focusY!!)

            invalidate()
            return super.onScale(detector)
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
            super.onScaleEnd(detector)
        }
    }

    private fun getMatrixValue(index: Int): Float {
        if (bitmapMatrix == null) {
            bitmapMatrix = imageMatrix
        }
        val values = FloatArray(9)
        bitmapMatrix.getValues(values)
        return values[index]
    }

    private val mScaleDetector = ScaleGestureDetector(context, scaleListener)

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        imageMatrix = bitmapMatrix
        mScaleDetector.onTouchEvent(ev)
        Log.d("ImageView", "pinchIn/Out")
        return true
    }

}
