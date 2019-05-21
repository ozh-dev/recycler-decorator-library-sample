package com.ozh.bunchdecorator.lib.decorators.layers

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.lib.decorators.Utils.forEach

class LayersProxy(
    private val underlay: List<Layer>,
    private val overlay: List<Layer>,
    private val offsets: List<Offset>
) {

    private fun viewTypePredicate(lViewItemType: Int, rViewItemType: Int): Boolean {
        return lViewItemType == rViewItemType || lViewItemType == UNDEFINE_VIEW_TYPE
    }

    fun onDraw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        forEach(recyclerView) { view, viewHolder, nextViewHolder ->
            underlay.filter { viewTypePredicate(it.viewItemType, viewHolder.itemViewType) }
                .forEach {
                    it.layerDrawer.draw(canvas, recyclerView, state, view, viewHolder, nextViewHolder)
                }
        }
    }

    fun onDrawOver(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        forEach(recyclerView) { view, viewHolder, nextViewHolder ->
            overlay.filter { viewTypePredicate(it.viewItemType, viewHolder.itemViewType) }
                .forEach {
                    it.layerDrawer.draw(canvas, recyclerView, state, view, viewHolder, nextViewHolder)
                }
        }
    }

    fun getItemOffsets(outRect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        val viewHolder = recyclerView.getChildViewHolder(view)
        val nexViewHolder = recyclerView.findViewHolderForAdapterPosition(viewHolder.adapterPosition + 1)
        offsets.filter { viewTypePredicate(it.viewItemType, viewHolder.itemViewType) }
            .forEach {
                it.offsetDrawer.getItemOffsets(outRect, view, recyclerView, state, viewHolder, nexViewHolder)
            }
    }
}