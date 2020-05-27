package ru.ozh.recycler.decorator.chat.decor

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.android.recycler.decorator.sample.easyadapter.chat.decor.ScrollProgressDecor
import ru.surfstudio.android.recycler.decorator.sample.toPx

class ScrollBarDecor() : ScrollProgressDecor() {

    private val paint = Paint()
        .apply {
            isAntiAlias = true
            color = Color.LTGRAY
        }

    private val progressBarRect = RectF()
    private var scrollRange = 0

    override fun draw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        progress: Float
    ) {
        if (scrollRange == 0) {
            scrollRange = recyclerView.computeVerticalScrollRange()
        }

        val barHeightRatio = recyclerView.computeVerticalScrollExtent().toFloat() / scrollRange
        val barHeight = barHeightRatio * recyclerView.measuredHeight

        canvas.save()
        val offset = progress * (canvas.height - barHeight)
        canvas.translate(0f, offset)
        with(progressBarRect) {
            this.top = 0f
            this.right = canvas.width.toFloat()
            this.bottom = this.top + barHeight
            this.left = (canvas.width - 8.toPx).toFloat()
        }

        canvas.drawRoundRect(progressBarRect, 8.toPx.toFloat(), 8.toPx.toFloat(), paint)
        canvas.restore()
    }
}