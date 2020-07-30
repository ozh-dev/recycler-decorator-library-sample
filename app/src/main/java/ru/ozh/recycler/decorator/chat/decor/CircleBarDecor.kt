package ru.ozh.recycler.decorator.chat.decor

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.recycler.decorator.px

class CircleBarDecor() : ScrollProgressDecor() {

    private val paint = Paint()
            .apply {
                isAntiAlias = true
                color = Color.LTGRAY
            }

    private val circleDiameter = 100.px

    private val progressBarRect = RectF()
    private var scrollRange = 0

    override fun draw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State, progress: Float) {

        val (cX, cY) = recyclerView.measuredWidth / 2 to recyclerView.measuredHeight / 2

        with(progressBarRect) {
            this.top = (cY - (circleDiameter / 2)).toFloat()
            this.right = (cX + (circleDiameter / 2)).toFloat()
            this.bottom = (cY + (circleDiameter / 2)).toFloat()
            this.left = (cX - (circleDiameter / 2)).toFloat()
        }

        canvas.drawArc(progressBarRect, 270f, progress * 360, true, paint)

    }
}