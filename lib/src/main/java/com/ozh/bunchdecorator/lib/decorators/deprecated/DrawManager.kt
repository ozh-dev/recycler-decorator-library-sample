package com.ozh.bunchdecorator.lib.decorators.deprecated

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.lib.decorators.sample.Rules
import com.ozh.bunchdecorator.lib.decorators.UNDEFINE_VIEW_HOLDER
import com.ozh.bunchdecorator.lib.decorators.Utils.forEach

@Deprecated("Use com.ozh.bunchdecorator.lib.decorators.layers.Decorator")
class DrawManager {

    private val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val alpha = dividerPaint.alpha

    fun onDraw(canvas: Canvas, recyclerView: RecyclerView) {
        forEach(recyclerView) { view, viewHolder, nextViewHolder ->
            if (viewHolder is DecorableViewHolder) {
                viewHolder.itemDivider?.let { itemDivider ->
                    val left = recyclerView.paddingLeft + itemDivider.paddingStart
                    val top = view.bottom + view.translationY
                    val right = recyclerView.width - recyclerView.paddingRight - itemDivider.paddingEnd
                    val bottom = view.bottom + itemDivider.height + view.translationY

                    dividerPaint.color = itemDivider.color
                    dividerPaint.strokeWidth = itemDivider.height.toFloat()
                    dividerPaint.alpha = (view.alpha * alpha).toInt()

                    val areSameHolders =
                        viewHolder.itemViewType == nextViewHolder?.itemViewType ?: UNDEFINE_VIEW_HOLDER

                    val drawMiddleDivider = Rules.checkAllRule(itemDivider.rule) && areSameHolders
                    val drawEndDivider = Rules.checkLastRule(itemDivider.rule) && areSameHolders.not()

                    if (drawMiddleDivider) {
                        canvas.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint)
                    } else if (drawEndDivider) {
                        canvas.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint)
                    }
                }
            }
        }
    }

    fun onDrawOver(canvas: Canvas, recyclerView: RecyclerView) {
        forEach(recyclerView) { view, viewHolder, nextViewHolder ->
            if (viewHolder is DecorableViewHolder) {
                viewHolder.itemDivider?.let { itemDivider ->
                    val startX = recyclerView.paddingLeft + itemDivider.paddingStart
                    val startY = view.bottom + view.translationY
                    val stopX = recyclerView.width - recyclerView.paddingRight - itemDivider.paddingEnd
                    val stopY = startY

                    dividerPaint.color = itemDivider.color
                    dividerPaint.strokeWidth = itemDivider.height.toFloat()
                    dividerPaint.alpha = (view.alpha * alpha).toInt()

                    val areSameHolders =
                        viewHolder.itemViewType == nextViewHolder?.itemViewType ?: UNDEFINE_VIEW_HOLDER

                    val drawMiddleDivider = Rules.checkAllRule(itemDivider.rule) && areSameHolders
                    val drawEndDivider = Rules.checkLastRule(itemDivider.rule) && areSameHolders.not()

                    if (drawMiddleDivider) {
                        canvas.drawLine(startX.toFloat(), startY, stopX.toFloat(), stopY, dividerPaint)
                    } else if (drawEndDivider) {
                        canvas.drawLine(startX.toFloat(), startY, stopX.toFloat(), stopY, dividerPaint)
                    }
                }
            }
        }
    }

    fun getItemOffsets(outRect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {

        val viewHolder = recyclerView.getChildViewHolder(view)

        if (viewHolder is DecorableViewHolder) {
            viewHolder.itemDivider?.let { itemDivider ->
                val viewHolderType = viewHolder.itemViewType
                val nextViewHolderAdapterPosition = viewHolder.adapterPosition + 1
                val nextViewHolderType = if(nextViewHolderAdapterPosition < recyclerView.adapter?.itemCount ?: 0) {
                    recyclerView.adapter?.getItemViewType(nextViewHolderAdapterPosition)
                } else {
                    UNDEFINE_VIEW_HOLDER
                }

                val areSameHolders = viewHolderType == nextViewHolderType
                val drawMiddleDivider = Rules.checkAllRule(itemDivider.rule) && areSameHolders
                val drawEndDivider = Rules.checkLastRule(itemDivider.rule) && areSameHolders.not()

                if (drawMiddleDivider) {
                    outRect.set(0, 0, 0, itemDivider.height) // left, top, right, bottom
                } else if(drawEndDivider) {
                    outRect.set(0, 0, 0, itemDivider.height) // left, top, right, bottom
                } else {
                    outRect.setEmpty()
                }
            }
        }
    }
}