package com.example.swipesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.viewModels
import com.example.swipesample.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private val MIN_SWIPE_DISTANCE_X = 100
    private val MAX_SWIPE_DISTANCE_X = 2000

    // タッチイベントを処理するためのインタフェース
    private var mGestureDetector: GestureDetector? = null

    private lateinit var binding : ActivityMainBinding

    private val viewModel:CountViewModel  = CountViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        mGestureDetector = GestureDetector(this, mOnGestureListener)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mGestureDetector!!.onTouchEvent(event)
    }

    private val mOnGestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            val deltaX: Float
            val deltaY: Float
            if (e1?.x != null && e2?.x != null && e1?.y != null && e2?.y != null) {
                deltaX = e1.x - e2.x
                deltaY = e1.y - e2.y
            } else {
                deltaX = 0.0f
                deltaY = 0.0f
            }
            val deltaXAbs = abs(deltaX)
            val deltaYAbs = abs(deltaY)

            if ((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X)) {
                if (deltaX > 0) {
                    Log.d("SwipeSample", "Swipe to left")
                    viewModel.countUp()

                } else {
                    Log.d("SwipeSample", "Swipe to right")
                    viewModel.countDown()

                }
            }

            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
}