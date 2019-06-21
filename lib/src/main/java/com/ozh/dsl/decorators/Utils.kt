package com.ozh.dsl.decorators

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.dsl.decorators.layers.LayerDrawer
import com.ozh.dsl.decorators.layers.OffsetDrawer

object Utils {
    inline fun forEach(
        recyclerView: RecyclerView,
        action: (view: View, viewHolder: RecyclerView.ViewHolder, nextViewHolder: RecyclerView.ViewHolder?) -> Unit
    ) {
        with(recyclerView) {
            val startPosition = 0
            val finishPosition = childCount
            for (position in startPosition until finishPosition) {
                val view = getChildAt(position)
                val viewHolder = getChildViewHolder(view)
                val nextViewHolder = findViewHolderForAdapterPosition(viewHolder.adapterPosition + 1)
                action(view, viewHolder, nextViewHolder)
            }
        }
    }
}

fun emptyLayerDrawer() = object : LayerDrawer() {
    override fun draw(canvas: Canvas, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        //empty
    }
}

fun emptyOffsetDrawer() = object : OffsetDrawer() {
    override fun getItemOffsets(outRect: Rect, childView: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        //empty
    }
}