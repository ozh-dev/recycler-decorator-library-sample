package com.ozh.dd.draw

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.ozh.dd.UNDEFINE_VIEW_TYPE

/**
 * Calls appropriate drawers for viewType
 */
class LayersBridge(
    private val underlays: List<DecorDrawer<LayerDrawer>>,
    private val overlays: List<DecorDrawer<LayerDrawer>>,
    private val offsets: List<DecorDrawer<OffsetDrawer>>
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
            ?.drawer
            ?.getItemOffsets(outRect, view, recyclerView, state)
    }

    private fun Map<Int, List<DecorDrawer<LayerDrawer>>>.drawTiedLayers(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {

        recyclerView.children.forEach { view ->
            val viewType = recyclerView.getChildViewHolder(view).itemViewType
            this[viewType]?.forEach {
                it.drawer.draw(canvas, view, recyclerView, state)
            }
        }
    }

    private fun Map<Int, List<DecorDrawer<LayerDrawer>>>.drawUnTiedLayers(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        recyclerView.children.forEach { view ->
            this[UNDEFINE_VIEW_TYPE]
                ?.forEach { it.drawer.draw(canvas, view, recyclerView, state) }
        }
    }

    private fun getVisibleViewTypes(recyclerView: RecyclerView) =
        recyclerView.children.map { view ->
            view to recyclerView.getChildViewHolder(view).itemViewType
        }
}