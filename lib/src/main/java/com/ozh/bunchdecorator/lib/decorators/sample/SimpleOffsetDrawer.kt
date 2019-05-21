package com.ozh.bunchdecorator.lib.decorators.sample

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.lib.decorators.layers.OffsetDrawer

class SimpleOffsetDrawer(private val offset: Int) : OffsetDrawer() {
    override fun getItemOffsets(
        outRect: Rect,
        childView: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder,
        nexViewHolder: RecyclerView.ViewHolder?
    ) {
        outRect.set(offset, offset / 2, offset, offset / 2)
    }
}