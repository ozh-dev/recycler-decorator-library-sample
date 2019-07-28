package com.ozh.dd.example.sample

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.dd.draw.OffsetDrawer

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