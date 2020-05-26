package ru.ozh.recycler.decorator.controllers

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//class LinePagerIndicatorDecoration : LayerDrawer {
//
//    private val colorActive = -0x1
//    private val colorInactive = 0x66FFFFFF
//
//    /**
//     * Height attachTo the space the indicator takes up at the bottom attachTo the view.
//     */
//    private val mIndicatorHeight = (DP * 16).toInt()
//
//    /**
//     * Indicator stroke width.
//     */
//    private val mIndicatorStrokeWidth = DP * 2
//
//    /**
//     * Indicator width.
//     */
//    private val mIndicatorItemLength = DP * 16
//    /**
//     * Padding between indicators.
//     */
//    private val mIndicatorItemPadding = DP * 4
//
//    /**
//     * Some more natural animation interpolation
//     */
//    private val mInterpolator = AccelerateDecelerateInterpolator()
//
//    private val mPaint = Paint()
//
//    init {
//        mPaint.strokeCap = Paint.Cap.ROUND
//        mPaint.strokeWidth = mIndicatorStrokeWidth
//        mPaint.style = Paint.Style.STROKE
//        mPaint.isAntiAlias = true
//    }
//
//
//    private fun drawInactiveIndicators(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, itemCount: Int) {
//        mPaint.color = colorInactive
//
//        // width attachTo item indicator including padding
//        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
//
//        var start = indicatorStartX
//        for (i in 0 until itemCount) {
//            // draw the line for every item
//            c.drawLine(start, indicatorPosY, start + mIndicatorItemLength, indicatorPosY, mPaint)
//            start += itemWidth
//        }
//    }
//
//    private fun drawHighlights(
//        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
//        highlightPosition: Int, progress: Float, itemCount: Int
//    ) {
//        mPaint.color = colorActive
//
//        // width attachTo item indicator including padding
//        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
//
//        if (progress == 0f) {
//            // no swipe, draw a normal indicator
//            val highlightStart = indicatorStartX + itemWidth * highlightPosition
//            c.drawLine(
//                highlightStart, indicatorPosY,
//                highlightStart + mIndicatorItemLength, indicatorPosY, mPaint
//            )
//        } else {
//            var highlightStart = indicatorStartX + itemWidth * highlightPosition
//            // calculate partial highlight
//            val partialLength = mIndicatorItemLength * progress
//
//            // draw the cut off highlight
//            c.drawLine(
//                highlightStart + partialLength, indicatorPosY,
//                highlightStart + mIndicatorItemLength, indicatorPosY, mPaint
//            )
//
//            // draw the highlight overlapping to the next item as well
//            if (highlightPosition < itemCount - 1) {
//                highlightStart += itemWidth
//                c.drawLine(
//                    highlightStart, indicatorPosY,
//                    highlightStart + partialLength, indicatorPosY, mPaint
//                )
//            }
//        }
//    }
//
//    override fun draw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
//        val itemCount = recyclerView.adapter!!.itemCount
//
//        // center horizontally, calculate width and subtract half from center
//        val totalLength = mIndicatorItemLength * itemCount
//        val paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding
//        val indicatorTotalWidth = totalLength + paddingBetweenItems
//        val indicatorStartX = (recyclerView.width - indicatorTotalWidth) / 2f
//
//        // center vertically in the allotted space
//        val indicatorPosY = recyclerView.height - mIndicatorHeight / 2f
//
//        drawInactiveIndicators(canvas, indicatorStartX, indicatorPosY, itemCount)
//
//
//        // find active page (which should be highlighted)
//        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
//        val activePosition = layoutManager!!.findFirstVisibleItemPosition()
//        if (activePosition == RecyclerView.NO_POSITION) {
//            return
//        }
//
//        // find offset attachTo active page (if the user is scrolling)
//        val activeChild = layoutManager.findViewByPosition(activePosition)
//        val left = activeChild!!.left
//        val width = activeChild.width
//
//        // on swipe the active item will be positioned from [-width, 0]
//        // interpolate offset for smooth animation
//        val progress = mInterpolator.getInterpolation(left * -1 / width.toFloat())
//
//        drawHighlights(canvas, indicatorStartX, indicatorPosY, activePosition, progress, itemCount)
//    }
//
//    companion object {
//
//        private val DP = Resources.getSystem().displayMetrics.density
//    }
//}
