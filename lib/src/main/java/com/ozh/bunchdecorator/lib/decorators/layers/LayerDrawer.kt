package com.ozh.bunchdecorator.lib.decorators.layers

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView

abstract class LayerDrawer {
    abstract fun draw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State)
}