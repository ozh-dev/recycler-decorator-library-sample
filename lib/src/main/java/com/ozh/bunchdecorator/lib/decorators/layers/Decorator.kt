package com.ozh.bunchdecorator.lib.decorators.layers

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

const val UNDEFINE_VIEW_TYPE = -1

class Decorator() {

    private val underlay: ArrayList<Layer> = arrayListOf()
    private val overlay: ArrayList<Layer> = arrayListOf()
    private val offsets: ArrayList<Offset> = arrayListOf()

    constructor(builder: Decorator.() -> Unit): this() {
        builder()
    }

    fun underlay(block: Layers.() -> Unit) {
        underlay.addAll(Layers().apply(block))
    }

    fun overlay(block: Layers.() -> Unit) {
        overlay.addAll(Layers().apply(block))
    }

    fun offsets(block: Offsets.() -> Unit) {
        offsets.addAll(Offsets().apply(block))
    }

    fun build(): ProxyDecorator {
        return ProxyDecorator(LayersProxy(underlay, overlay, offsets))
    }
}

class Layers : ArrayList<Layer>() {
    fun layer(block: LayerBuilder.() -> Unit) {
        add(LayerBuilder().apply(block).build())
    }
}

class Offsets : ArrayList<Offset>() {
    fun offset(block: OffsetBuilder.() -> Unit) {
        add(OffsetBuilder().apply(block).build())
    }
}

class LayerBuilder {

    var viewItemType: Int = UNDEFINE_VIEW_TYPE

    var layerDrawer: LayerDrawer = object : LayerDrawer() {
        override fun draw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            state: RecyclerView.State,
            view: View,
            viewHolder: RecyclerView.ViewHolder,
            nextViewHolder: RecyclerView.ViewHolder?
        ) {
            //empty
        }
    }

    fun build() : Layer =
        Layer(viewItemType, layerDrawer)
}

class OffsetBuilder {

    var viewItemType: Int = UNDEFINE_VIEW_TYPE

    var offsetDrawer: OffsetDrawer = object : OffsetDrawer() {
        override fun getItemOffsets(
            outRect: Rect,
            childView: View,
            recyclerView: RecyclerView,
            state: RecyclerView.State,
            viewHolder: RecyclerView.ViewHolder,
            nexViewHolder: RecyclerView.ViewHolder?
        ) {
            //empty
        }
    }

    fun build() : Offset =
        Offset(viewItemType, offsetDrawer)
}