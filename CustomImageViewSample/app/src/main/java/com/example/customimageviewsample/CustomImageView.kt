package com.example.customimageviewsample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.max
import kotlin.math.min

class CustomImageView: AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private var mScaleFactor = 1f

    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mScaleFactor *= detector.scaleFactor

            // Don't let the object get too small or too large.
            mScaleFactor = max(0.1f, min(mScaleFactor, 5.0f))

            scaleX = mScaleFactor
            scaleY = mScaleFactor

            invalidate()
            return true
        }
    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//
//        canvas?.apply {
//            save()
//            scale(mScaleFactor, mScaleFactor)
//            // onDraw() code goes here
//            restore()
//        }
//    }


    //できなかった
/*
    private var bitmap: Bitmap? = null
    private val bitmapMatrix = Matrix()
    private val paint = Paint()


    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        private var scale = 1f
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            scale = 1f
            return super.onScaleBegin(detector)
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val deltaScale = detector.scaleFactor / scale
            bitmapMatrix.postScale(deltaScale, deltaScale, detector.focusX, detector.focusY)
            scale = detector.scaleFactor
            invalidate()
            return super.onScale(detector)
        }
    }

    override fun onDraw(canvas: Canvas) {
        bitmap?.let { bitmap ->
            canvas.save()
            canvas.drawBitmap(bitmap, bitmapMatrix, paint)
            canvas.restore()
        }
    }

 */

    private val mScaleDetector = ScaleGestureDetector(context, scaleListener)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        mScaleDetector.onTouchEvent(ev)
        Log.d("ImageView", "pinchIn/Out")
        return true
    }
}