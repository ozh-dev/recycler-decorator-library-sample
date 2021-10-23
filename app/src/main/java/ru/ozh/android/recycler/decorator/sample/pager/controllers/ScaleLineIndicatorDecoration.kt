package ru.ozh.android.recycler.decorator.sample.pager.controllers

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.sample.pager.controllers.IndicatorAlignHorizontal.CENTER
import ru.ozh.android.recycler.decorator.sample.pager.controllers.IndicatorAlignHorizontal.LEFT
import ru.ozh.android.recycler.decorator.sample.pager.controllers.IndicatorAlignHorizontal.RIGHT
import kotlin.math.abs
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.pager.controllers.IndicatorAlignVertical.*

const val MIN_SHOW_INDICATOR_VALUE = 1

/**
 * Can use with ViewPager2 and RecyclerView with PagerSnapHelper
 *
 * @property context [Context]
 * @property hasInfiniteScroll use for infinite pager
 * @property alignHorizontal horizontal align [IndicatorAlignHorizontal]
 * @property alignVertical vertical align [IndicatorAlignVertical]
 * @param widthInactiveResource width inactive indicator
 * @param widthActiveResource width active indicator
 * @param paddingBetweenResource offset between indicators
 * @param indicatorHeightResource indicator's height
 * @param paddingHorizontalResource horizontal padding
 * @param indicatorRadiusResource indicator radius
 * @param paddingBottomResource bottom padding
 * @param paddingTopResource top padding
 * @param colorActiveResource color of active indicator
 * @param colorActiveResource color of inactive indicator
 */
open class ScaleLinePageIndicatorDecoration(
    private val context: Context,
    private val hasInfiniteScroll: Boolean = false,
    private val alignHorizontal: IndicatorAlignHorizontal = LEFT,
    private val alignVertical: IndicatorAlignVertical = BOTTOM,
    @DimenRes widthInactiveResource: Int = R.dimen.page_indicator_inactive_width,
    @DimenRes widthActiveResource: Int = R.dimen.page_indicator_active_width,
    @DimenRes paddingBetweenResource: Int = R.dimen.page_indicator_padding_between,
    @DimenRes indicatorHeightResource: Int = R.dimen.page_indicator_height,
    @DimenRes paddingHorizontalResource: Int = R.dimen.page_indicator_padding_horizontal,
    @DimenRes indicatorRadiusResource: Int = R.dimen.page_indicator_radius,
    @DimenRes paddingTopResource: Int = R.dimen.page_indicator_padding_top,
    @DimenRes paddingBottomResource: Int = R.dimen.page_indicator_padding_bottom,
    @ColorRes colorActiveResource: Int = R.color.black,
    @ColorRes colorInactiveResource: Int = R.color.black_12
) : Decorator.RecyclerViewDecor {

    private val indicatorPaint = Paint()
    private val rect = Rect()
    private val widthInactive: Int = context.resources.getDimensionPixelSize(widthInactiveResource)
    private val widthActive: Int = context.resources.getDimensionPixelSize(widthActiveResource)
    private val paddingBetween: Int = context.resources.getDimensionPixelSize(paddingBetweenResource)
    private val indicatorHeight: Int = context.resources.getDimensionPixelSize(indicatorHeightResource)
    private val indicatorRadius: Int = context.resources.getDimensionPixelSize(indicatorRadiusResource)
    private val paddingTop: Int = context.resources.getDimensionPixelSize(paddingTopResource)
    private val paddingBottom: Int = context.resources.getDimensionPixelSize(paddingBottomResource)
    private val paddingHorizontal: Int = context.resources.getDimensionPixelSize(paddingHorizontalResource)
    private val indicatorDiff: Int = widthActive - widthInactive

    val colorDefaultActive: Int = ContextCompat.getColor(context, colorActiveResource)
    val colorDefaultInactive: Int = ContextCompat.getColor(context, colorInactiveResource)

//    val colorWhiteActive: Int = context.resources.getColor(R.color.pageWhiteIndicatorActiveColor)
//    val colorWhiteInactive: Int = context.resources.getColor(R.color.pageWhiteIndicatorInactiveColor)

    var colorActive: Int = colorDefaultActive
    var colorInactive: Int = colorDefaultInactive

    var isInfiniteScrollEnabled = hasInfiniteScroll

    val colorEvaluator = ArgbEvaluator()

    val colorPositions: MutableList<Boolean> = mutableListOf()

    init {
        indicatorPaint.isAntiAlias = true
    }

    override fun draw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapter = recyclerView.adapter as EasyAdapter
        val itemsCount = if (isInfiniteScrollEnabled) {
            adapter.itemCount / EasyAdapter.INFINITE_SCROLL_LOOPS_COUNT
        } else {
            adapter.itemCount
        }

        if (colorPositions.size != itemsCount) {
            colorPositions.clear()
            repeat(itemsCount) {
                colorPositions.add(false)
            }
        }

        if (itemsCount <= MIN_SHOW_INDICATOR_VALUE) {
            return
        }

        val totalLength = widthInactive * (itemsCount - 1) + widthActive
        val paddingBetweenItems = (itemsCount - 1) * paddingBetween
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = when (alignHorizontal) {
            LEFT -> paddingHorizontal.toFloat()
            CENTER -> (recyclerView.width - indicatorTotalWidth) / 2f
            RIGHT -> recyclerView.width - indicatorTotalWidth - paddingHorizontal.toFloat()
        }

        val indicatorPosY = when(alignVertical) {
            TOP -> paddingTop + indicatorHeight
            BOTTOM -> recyclerView.height - paddingBottom - indicatorHeight
        }.toFloat()

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition()
        val activePosition = if (firstVisibleItemPosition != null) {
            firstVisibleItemPosition % itemsCount
        } else {
            RecyclerView.NO_POSITION
        }
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }
        recyclerView.getDecoratedBoundsWithMargins(recyclerView[0], rect)
        val progress = abs(rect.left.toFloat() / rect.width())
        drawIndicators(
            canvas,
            indicatorStartX,
            indicatorPosY,
            itemsCount,
            activePosition,
            progress
        )
    }

    private fun drawIndicators(
        canvas: Canvas,
        startX: Float,
        startY: Float,
        itemsCount: Int,
        activePosition: Int,
        progress: Float
    ) {
        val diff = indicatorDiff * progress
        var currentStartX = startX
        for (index in 0 until itemsCount) {
            val indicatorWidth = calculateIndicatorWidth(index, activePosition, itemsCount, diff)
            indicatorPaint.color = calculateColor(index, activePosition, itemsCount, progress)
            val rect = RectF(currentStartX, startY - indicatorHeight, currentStartX + indicatorWidth, startY)
            canvas.drawRoundRect(rect, indicatorRadius.toFloat(), indicatorRadius.toFloat(), indicatorPaint)
            currentStartX += (indicatorWidth + paddingBetween)
        }
    }

    private fun calculateIndicatorWidth(index: Int, activePosition: Int, itemsCount: Int, diff: Float): Float {
        return when (index) {
            activePosition % itemsCount -> {
                widthActive - diff
            }
            (activePosition + 1) % itemsCount -> {
                widthInactive + diff
            }
            else -> {
                widthInactive.toFloat()
            }
        }
    }

    @ColorInt
    protected open fun calculateColor(index: Int, activePosition: Int, itemsCount: Int, progress: Float): Int {
        return when (index) {
            activePosition % itemsCount -> {
                colorEvaluator.evaluate(
                    progress,
                    colorActive,
                    colorInactive
                ) as Int
            }
            (activePosition + 1) % itemsCount -> {
                colorEvaluator.evaluate(
                    progress,
                    colorInactive,
                    colorActive
                ) as Int
            }
            else -> {
                colorInactive
            }
        }
    }
}

enum class IndicatorAlignHorizontal {
    LEFT, CENTER, RIGHT
}

enum class IndicatorAlignVertical {
    TOP, BOTTOM
}