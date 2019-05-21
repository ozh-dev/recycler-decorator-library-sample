package com.ozh.bunchdecorator.lib.decorators.deprecated

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.lib.decorators.UNDEFINE_VIEW_HOLDER
import com.ozh.bunchdecorator.lib.decorators.round.RoundMode
import com.ozh.bunchdecorator.lib.decorators.round.RoundMode.*
import com.ozh.bunchdecorator.lib.decorators.round.RoundOutlineProvider

/**
 * [RecyclerView.ItemDecoration] для рисования закруглений у контроллеров.
 * Работает вместе с [DecorableViewHolder]
 */

@Deprecated("Use com.ozh.bunchdecorator.lib.decorators.layers.Decorator")
class RounderControllerDecorator : RecyclerView.ItemDecoration() {

    private val clipOutlineProvider = RoundOutlineProvider()

    override fun onDraw(canvas: Canvas, recyclerView: RecyclerView) {
        super.onDrawOver(canvas, recyclerView)

        val startPosition = 0
        val finishPosition = recyclerView.childCount

        for (position in startPosition until finishPosition) {
            val childView = recyclerView.getChildAt(position)
            val childViewHolder = recyclerView.getChildViewHolder(childView)
            val previousChildViewHolder = recyclerView.findViewHolderForAdapterPosition(childViewHolder.position - 1)
            val nextChildViewHolder = recyclerView.findViewHolderForAdapterPosition(childViewHolder.position + 1)

            if (childViewHolder is DecorableViewHolder) {
                childViewHolder.cornerRadius.let { radius->
                    if (radius.compareTo(0f) != 0) {
                        clipOutlineProvider.outlineRadius = radius
                        clipOutlineProvider.roundMode = getRoundMode(previousChildViewHolder, childViewHolder, nextChildViewHolder)
                        if(childView.outlineProvider is RoundOutlineProvider) {
                            childView.invalidateOutline()
                        } else {
                            childView.outlineProvider = clipOutlineProvider
                            childView.clipToOutline = true
                        }
                    }
                }
            }
        }
    }

    private fun getRoundMode(
            previousChildViewHolder: RecyclerView.ViewHolder?,
            currentViewHolder: RecyclerView.ViewHolder?,
            nextChildViewHolder: RecyclerView.ViewHolder?
    ): RoundMode {

        val previousHolderItemType = previousChildViewHolder?.itemViewType ?: UNDEFINE_VIEW_HOLDER
        val currentHolderItemType = currentViewHolder?.itemViewType ?: UNDEFINE_VIEW_HOLDER
        val nextHolderItemType = nextChildViewHolder?.itemViewType ?: UNDEFINE_VIEW_HOLDER

        return when {
            previousHolderItemType != currentHolderItemType && currentHolderItemType != nextHolderItemType -> ALL
            previousHolderItemType != currentHolderItemType && currentHolderItemType == nextHolderItemType -> TOP
            previousHolderItemType == currentHolderItemType && currentHolderItemType != nextHolderItemType -> BOTTOM
            previousHolderItemType == currentHolderItemType && currentHolderItemType == nextHolderItemType -> NONE
            else -> NONE
        }
    }
}