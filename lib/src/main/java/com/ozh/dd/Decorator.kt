package com.ozh.dd

import androidx.recyclerview.widget.RecyclerView
import com.ozh.dd.draw.*

/**
 * The main builder for underlay, overlay and offsets
 */
class Decorator {

    companion object {
        fun decorator(builder: Decorator.() -> Unit): RecyclerView.ItemDecoration {
            return Decorator().apply(builder).build()
        }
    }

    /**
     * Scope of underlay layers
     */
    private var underlayScope: ScopeBuilder<DecorDrawer<LayerDrawer>> =
        ScopeBuilder()

    /**
     * Scope of overlay layers
     */
    private var overlayScope: ScopeBuilder<DecorDrawer<LayerDrawer>> =
        ScopeBuilder()

    /**
     * Scope of offsets
     */
    private var offsetsScope: ScopeBuilder<DecorDrawer<OffsetDrawer>> =
        ScopeBuilder()

    /**
     * Collects underlay layers
     */
    fun underlay(addLayers: ScopeBuilder<DecorDrawer<LayerDrawer>>.() -> Unit) {
        underlayScope.apply(addLayers)
    }

    /**
     * Collects overlay layers
     */
    fun overlay(addLayers: ScopeBuilder<DecorDrawer<LayerDrawer>>.() -> Unit) {
        overlayScope.apply(addLayers)
    }

    /**
     * Collects offsets
     */
    fun offsets(addOffsets: ScopeBuilder<DecorDrawer<OffsetDrawer>>.() -> Unit) {
        offsetsScope.apply(addOffsets)
    }

    /**
     * Extension for collect layer drawers
     */
    fun ScopeBuilder<DecorDrawer<LayerDrawer>>.layer(layerDrawer: DecorDrawer.Builder<LayerDrawer>.() -> Unit) {
        add(DecorDrawer.Builder<LayerDrawer>().apply(layerDrawer).build())
    }

    /**
     * Extension for collect offset drawers
     */
    fun ScopeBuilder<DecorDrawer<OffsetDrawer>>.offset(offsetDrawer: DecorDrawer.Builder<OffsetDrawer>.() -> Unit) {
        add(DecorDrawer.Builder<OffsetDrawer>().apply(offsetDrawer).build())
    }

    /**
     * Builds all layers to [androidx.recyclerview.widget.RecyclerView.ItemDecoration]
     */
    fun build(): MasterDecorator {
        val underlay = underlayScope.build()
        val overlay = overlayScope.build()
        val offsets = offsetsScope.build()

        require(
            offsets.groupingBy { it.viewItemType }.eachCount().all { it.value == 1 }
        ) { "Any ViewHolder can have only a single OffsetDrawer" }

        return MasterDecorator(LayersBridge(underlay, overlay, offsets))
    }
}