package com.ozh.bunchdecorator.lib.decorators.deprecated

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozh.bunchdecorator.lib.decorators.deprecated.DrawManager

class LinearItemsDecoration(var drawOver: Boolean = false) : RecyclerView.ItemDecoration() {

    private val drawManager = DrawManager()

    override fun getItemOffsets(outRect: Rect, childView: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, childView, recyclerView, state)
        if (drawOver.not()) {
            drawManager.getItemOffsets(outRect, childView, recyclerView, state)
        }
    }

    override fun onDraw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, recyclerView, state)
        if(drawOver.not()) {
            drawManager.onDraw(canvas, recyclerView)
        }
    }

    override fun onDrawOver(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, recyclerView, state)
        if (drawOver) {
            drawManager.onDrawOver(canvas, recyclerView)
        }
    }
}