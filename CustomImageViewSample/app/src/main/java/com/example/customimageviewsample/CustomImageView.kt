package com.example.customimageviewsample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.GestureDetectorCompat
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

            //小さくならないように1.0-5.0のscaleに収める
            mScaleFactor = max(1.0f, min(mScaleFactor, 5.0f))

            //この２行で描写できるようになってるぽい？
            scaleX = mScaleFactor
            scaleY = mScaleFactor

//            invalidate()
            return true
        }
    }

    //移動
    private val panListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {

            translationX -= distanceX
            translationY -= distanceY

//            invalidate()
            return true
        }
    }

     private val mPanGestureDetector = GestureDetectorCompat(context, panListener)


    private val mScaleDetector = ScaleGestureDetector(context, scaleListener)


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        mScaleDetector.onTouchEvent(ev)
        mPanGestureDetector.onTouchEvent(ev)
        return true
    }


//公式ドキュメントに含まれてたけどonScale内のscale設定だけで描写できてた
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            save()
            scale(mScaleFactor, mScaleFactor)
            // onDraw() code goes here
            restore()
        }
    }


/*

別のサンプル
表示する画像をsetBitmapで渡す？

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

    fun setBitmap(bitmap: Bitmap?) {
        if (bitmap != null) {
            this.bitmap = bitmap
            val mScaleFactor = min(width.toFloat() / bitmap.width, height.toFloat() / bitmap.height)
            bitmapMatrix.setScale(mScaleFactor, mScaleFactor, 0f, 0f)
        }
    }

 */

}