/*
 * Copyright 2019 Oleg Zhilo
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.ozh.dd.draw

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.ozh.dd.EACH_VIEW
import com.ozh.dd.RECYCLER_VIEW

/**
 * Calls appropriate drawers for viewType
 */
class LayersBridge(
    underlays: List<DecorDrawer<LayerDrawer>>,
    overlays: List<DecorDrawer<LayerDrawer>>,
    offsets: List<DecorDrawer<OffsetDrawer>>
) {

    private val groupedUnderlays = underlays.groupBy { it.viewItemType }
    private val groupedOverlays = overlays.groupBy { it.viewItemType }
    private val associatedOffsets = offsets.associateBy { it.viewItemType }

    fun onDrawUnderlay(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        groupedUnderlays.drawRecyclerViewDecors(canvas, recyclerView, state)
        groupedUnderlays.drawAttachedDecors(canvas, recyclerView, state)
        groupedUnderlays.drawNotAttachedDecors(canvas, recyclerView, state)
    }

    fun onDrawOverlay(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        groupedOverlays.drawAttachedDecors(canvas, recyclerView, state)
        groupedOverlays.drawNotAttachedDecors(canvas, recyclerView, state)
        groupedOverlays.drawRecyclerViewDecors(canvas, recyclerView, state)
    }

    fun getItemOffsets(outRect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        drawOffset(EACH_VIEW, outRect, view, recyclerView, state)
        val itemViewType = recyclerView.findContainingViewHolder(view)?.itemViewType ?: EACH_VIEW
        drawOffset(itemViewType, outRect, view, recyclerView, state)
    }

    private fun Map<Int, List<DecorDrawer<LayerDrawer>>>.drawAttachedDecors(
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

    private fun Map<Int, List<DecorDrawer<LayerDrawer>>>.drawNotAttachedDecors(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        recyclerView.children.forEach { view ->
            this[EACH_VIEW]
                ?.forEach { it.drawer.draw(canvas, view, recyclerView, state) }
        }
    }

    private fun Map<Int, List<DecorDrawer<LayerDrawer>>>.drawRecyclerViewDecors(
        canvas: Canvas,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        this[RECYCLER_VIEW]
            ?.forEach { it.drawer.draw(canvas, recyclerView, state) }
    }

    private fun drawOffset(viewType: Int, outRect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        associatedOffsets[viewType]
            ?.drawer
            ?.getItemOffsets(outRect, view, recyclerView, state)
    }
}