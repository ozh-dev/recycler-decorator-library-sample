package com.ozh.dd

import androidx.recyclerview.widget.RecyclerView
import com.ozh.dd.draw.*

class Decorator {

    companion object {
        fun decorator(builder: Decorator.() -> Unit): RecyclerView.ItemDecoration {
            return Decorator().apply(builder).build()
        }
    }

    private var underlayScope: ScopeBuilder<DecorDrawer<LayerDrawer>> =
        ScopeBuilder()
    private var overlayScope: ScopeBuilder<DecorDrawer<LayerDrawer>> =
        ScopeBuilder()
    private var offsetsScope: ScopeBuilder<DecorDrawer<OffsetDrawer>> =
        ScopeBuilder()

    fun underlay(addLayers: ScopeBuilder<DecorDrawer<LayerDrawer>>.() -> Unit) {
        underlayScope.apply(addLayers)
    }

    fun overlay(addLayers: ScopeBuilder<DecorDrawer<LayerDrawer>>.() -> Unit) {
        overlayScope.apply(addLayers)
    }

    fun offsets(addOffsets: ScopeBuilder<DecorDrawer<OffsetDrawer>>.() -> Unit) {
        offsetsScope.apply(addOffsets)
    }

    fun ScopeBuilder<DecorDrawer<LayerDrawer>>.layer(layerDrawer: DecorDrawer.Builder<LayerDrawer>.() -> Unit) {
        add(DecorDrawer.Builder<LayerDrawer>().apply(layerDrawer).build())
    }

    fun ScopeBuilder<DecorDrawer<OffsetDrawer>>.offset(offsetDrawer: DecorDrawer.Builder<OffsetDrawer>.() -> Unit) {
        add(DecorDrawer.Builder<OffsetDrawer>().apply(offsetDrawer).build())
    }

    fun build(): MasterDecorator {
        val underlay = underlayScope.build()
        val overlay = overlayScope.build()
        val offsets = offsetsScope.build()

        require(
            offsets.groupingBy { it.viewItemType }.eachCount().all { it.value == 1 }
        ) { "Any ViewHolder can have only a single OffsetDrawer" }

        return MasterDecorator(
            LayersBridge(
                underlay,
                overlay,
                offsets
            )
        )
    }

}