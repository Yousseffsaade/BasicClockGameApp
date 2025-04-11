package com.example.basicclockgameapp

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class ClockLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val width = right - left
        val height = bottom - top

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = 0.4f * min(width, height) // rayon = 40% du plus petit côté

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            if (child is Button) {
                val angle = Math.toRadians(270.0 - i * 30.0)
                val x = centerX + radius * cos(angle).toFloat() - child.measuredWidth / 2
                val y = centerY + radius * sin(angle).toFloat() - child.measuredHeight / 2
                child.layout(
                    x.toInt(),
                    y.toInt(),
                    (x + child.measuredWidth).toInt(),
                    (y + child.measuredHeight).toInt()
                )
            } else {
                val bgLeft = (centerX - child.measuredWidth / 2).toInt()
                val bgTop = (centerY - child.measuredHeight / 2).toInt()
                child.layout(
                    bgLeft,
                    bgTop,
                    bgLeft + child.measuredWidth,
                    bgTop + child.measuredHeight
                )
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
    }
}
