package com.ozh.dsl.decorators.layers

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class LayerDrawer {
    abstract fun draw(
        canvas: Canvas,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    )
}