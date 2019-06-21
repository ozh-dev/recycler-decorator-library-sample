package com.ozh.dsl.decorators.layers

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MasterDecorator(private val layersBridge: LayersBridge) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        layersBridge.onDrawOverlay(canvas, parent, state)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        layersBridge.onDrawUnderlay(canvas, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        layersBridge.getItemOffsets(outRect, view, parent, state)
    }
}