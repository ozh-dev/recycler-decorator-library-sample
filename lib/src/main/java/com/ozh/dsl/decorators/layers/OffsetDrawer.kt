package com.ozh.dsl.decorators.layers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class OffsetDrawer {
    abstract fun getItemOffsets(
        outRect: Rect,
        childView: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    )
}