package com.ozh.bunchdecorator.example.controllers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.lib.decorators.layers.OffsetDrawer

class MyOffsetDrawer : OffsetDrawer() {
    override fun getItemOffsets(outRect: Rect, childView: View, recyclerView: RecyclerView, state: RecyclerView.State) {

    }
}