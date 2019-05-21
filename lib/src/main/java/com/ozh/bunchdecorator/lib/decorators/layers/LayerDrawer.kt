package com.ozh.bunchdecorator.lib.decorators.layers

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class LayerDrawer {
    abstract fun draw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        view: View,
        viewHolder: RecyclerView.ViewHolder,
        nextViewHolder: RecyclerView.ViewHolder?
    )
}