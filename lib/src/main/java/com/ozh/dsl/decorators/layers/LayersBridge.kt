package com.ozh.dsl.decorators.layers

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class LayersBridge(
    private val underlays: List<Layer>,
    private val overlays: List<Layer>,
    private val offsets: List<Offset>
) {

    private val groupedUnderlays = underlays.groupBy { it.viewItemType }
    private val groupedOverlays = overlays.groupBy { it.viewItemType }
    private val associatedOffsets = offsets.associateBy { it.viewItemType }

    fun onDrawUnderlay(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        groupedUnderlays.drawTiedLayers(canvas, recyclerView, state)
        groupedUnderlays.drawUnTiedLayers(canvas, recyclerView, state)
    }

    fun onDrawOverlay(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        groupedOverlays.drawTiedLayers(canvas, recyclerView, state)
        groupedOverlays.drawUnTiedLayers(canvas, recyclerView, state)
    }

    fun getItemOffsets(outRect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        val vh = recyclerView.findContainingViewHolder(view)
        associatedOffsets[vh?.itemViewType ?: UNDEFINE_VIEW_TYPE]
            ?.offsetDrawer
            ?.getItemOffsets(outRect, view, recyclerView, state)
    }

    private fun Map<Int, List<Layer>>.drawTiedLayers(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {

        recyclerView.children.forEach { view ->
            val viewType = recyclerView.getChildViewHolder(view).itemViewType
            this[viewType]?.forEach {
                it.layerDrawer.draw(canvas, view, recyclerView, state)
            }
        }
    }

    private fun Map<Int, List<Layer>>.drawUnTiedLayers(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        recyclerView.children.forEach { view ->
            this[UNDEFINE_VIEW_TYPE]
                ?.forEach { it.layerDrawer.draw(canvas, view, recyclerView, state) }
        }
    }

    private fun getVisibleViewTypes(recyclerView: RecyclerView) =
        recyclerView.children.map { view ->
            view to recyclerView.getChildViewHolder(view).itemViewType
        }
}