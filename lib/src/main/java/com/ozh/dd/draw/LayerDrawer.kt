package com.ozh.dd.draw

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstract class for implementation LayerDrawer
 */
abstract class LayerDrawer {
    abstract fun draw(
        canvas: Canvas,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    )
}