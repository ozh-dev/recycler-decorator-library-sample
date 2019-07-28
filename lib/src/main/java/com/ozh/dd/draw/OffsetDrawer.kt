package com.ozh.dd.draw

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstract class for implementation OffsetDrawer
 */
abstract class OffsetDrawer {
    abstract fun getItemOffsets(
        outRect: Rect,
        childView: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    )
}