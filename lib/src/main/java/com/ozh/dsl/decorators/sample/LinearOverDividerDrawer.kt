package com.ozh.dsl.decorators.sample

import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.dsl.decorators.UNDEFINE_VIEW_HOLDER
import com.ozh.dsl.decorators.layers.LayerDrawer

class LinearOverDividerDrawer(private val gap: Gap) : LayerDrawer() {

    private val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val alpha = dividerPaint.alpha

    init {
        dividerPaint.color = gap.color
        dividerPaint.strokeWidth = gap.height.toFloat()
    }

    //TODO horizontal and vertical logic, also reverse state
    override fun draw(
        canvas: Canvas,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = recyclerView.getChildViewHolder(view)
        val nextViewHolder = recyclerView.findViewHolderForAdapterPosition(viewHolder.adapterPosition + 1)

        val startX = recyclerView.paddingLeft + gap.paddingStart
        val startY = view.bottom + view.translationY
        val stopX = recyclerView.width - recyclerView.paddingRight - gap.paddingEnd
        val stopY = startY

        dividerPaint.alpha = (view.alpha * alpha).toInt()

        val areSameHolders =
            viewHolder.itemViewType == nextViewHolder?.itemViewType ?: UNDEFINE_VIEW_HOLDER

        val drawMiddleDivider = Rules.checkMiddleRule(gap.rule) && areSameHolders
        val drawEndDivider = Rules.checkEndRule(gap.rule) && areSameHolders.not()

        if (drawMiddleDivider) {
            canvas.drawLine(startX.toFloat(), startY, stopX.toFloat(), stopY, dividerPaint)
        } else if (drawEndDivider) {
            canvas.drawLine(startX.toFloat(), startY, stopX.toFloat(), stopY, dividerPaint)
        }
    }
}