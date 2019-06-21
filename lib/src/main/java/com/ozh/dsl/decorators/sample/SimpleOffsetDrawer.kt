package com.ozh.dsl.decorators.sample

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.dsl.decorators.layers.OffsetDrawer

class SimpleOffsetDrawer(private val offset: Int) : OffsetDrawer() {

    override fun getItemOffsets(
        outRect: Rect,
        childView: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(offset, offset / 2, offset, offset / 2)
    }
}