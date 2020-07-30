package ru.ozh.recycler.decorator.pager.controllers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.android.recycler.decorator.Decorator
import ru.ozh.recycler.decorator.px

class LinePagerIndicatorDecoration : Decorator.RecyclerViewDecor {

    private val colorActive = Color.GREEN
    private val colorInactive = Color.WHITE

    private val indicatorHeight = 16.px

    /**
     * Indicator stroke width.
     */
    private val indicatorStrokeWidth = 2.px

    /**
     * Indicator width.
     */
    private val indicatorItemLength = 16.px
    /**
     * Padding between indicators.
     */
    private val indicatorItemPadding = 4.px

    /**
     * Some more natural animation interpolation
     */
    private val interpolator = AccelerateDecelerateInterpolator()

    private val paint = Paint()

    init {
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = indicatorStrokeWidth.toFloat()
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
    }

    private fun drawInactiveIndicators(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, itemCount: Int) {
        paint.color = colorInactive

        // width attachTo item indicator including padding
        val itemWidth = indicatorItemLength + indicatorItemPadding

        var start = indicatorStartX
        for (i in 0 until itemCount) {
            // draw the line for every item
            c.drawLine(start, indicatorPosY, start + indicatorItemLength, indicatorPosY, paint)
            start += itemWidth
        }
    }

    private fun drawHighlights(
        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
        highlightPosition: Int, progress: Float, itemCount: Int
    ) {
        paint.color = colorActive

        // width attachTo item indicator including padding
        val itemWidth = indicatorItemLength + indicatorItemPadding

        if (progress == 0f) {
            // no swipe, draw a normal indicator
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            c.drawLine(
                highlightStart, indicatorPosY,
                highlightStart + indicatorItemLength, indicatorPosY, paint
            )
        } else {
            var highlightStart = indicatorStartX + itemWidth * highlightPosition
            // calculate partial highlight
            val partialLength = indicatorItemLength * progress

            // draw the cut off highlight
            c.drawLine(
                highlightStart + partialLength, indicatorPosY,
                highlightStart + indicatorItemLength, indicatorPosY, paint
            )

            // draw the highlight overlapping to the next item as well
            if (highlightPosition < itemCount - 1) {
                highlightStart += itemWidth
                c.drawLine(
                    highlightStart, indicatorPosY,
                    highlightStart + partialLength, indicatorPosY, paint
                )
            }
        }
    }

    override fun draw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = recyclerView.adapter!!.itemCount

        // center horizontally, calculate width and subtract half from center
        val totalLength = indicatorItemLength * itemCount
        val paddingBetweenItems = Math.max(0, itemCount - 1) * indicatorItemPadding
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (recyclerView.width - indicatorTotalWidth) / 2f

        // center vertically in the allotted space
        val indicatorPosY = recyclerView.height - indicatorHeight / 2f

        drawInactiveIndicators(canvas, indicatorStartX, indicatorPosY, itemCount)


        // find active page (which should be highlighted)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        val activePosition = layoutManager!!.findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }

        // find offset attachTo active page (if the user is scrolling)
        val activeChild = layoutManager.findViewByPosition(activePosition)
        val left = activeChild!!.left
        val width = activeChild.width

        // on swipe the active item will be positioned from [-width, 0]
        // interpolate offset for smooth animation
        val progress = interpolator.getInterpolation(left * -1 / width.toFloat())

        drawHighlights(canvas, indicatorStartX, indicatorPosY, activePosition, progress, itemCount)
    }
}
