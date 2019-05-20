package com.ozh.bunchdecorator.lib.decorators.layers

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LayersBridge(
    val underlay: ArrayList<Layer> = arrayListOf(),
    val overlay: ArrayList<Layer> = arrayListOf(),
    val offsets: ArrayList<Offset> = arrayListOf()
) {
    fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

    }

    fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

    }

    fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

    }

}