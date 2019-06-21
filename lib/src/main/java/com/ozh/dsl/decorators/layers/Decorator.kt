package com.ozh.dsl.decorators.layers

import com.ozh.dsl.decorators.emptyLayerDrawer
import com.ozh.dsl.decorators.emptyOffsetDrawer

const val UNDEFINE_VIEW_TYPE = -1

class Decorator() {

    private val underlay: ArrayList<Layer> = arrayListOf()
    private val overlay: ArrayList<Layer> = arrayListOf()
    private val offsets: ArrayList<Offset> = arrayListOf()

    constructor(builder: Decorator.() -> Unit) : this() {
        builder()
    }

    fun underlay(block: Layers.() -> Unit) {
        underlay.addAll(Layers().apply(block))
    }

    fun overlay(block: Layers.() -> Unit) {
        overlay.addAll(Layers().apply(block))
    }

    fun offsets(block: Offsets.() -> Unit) {
        val elements = Offsets().apply(block)
        require(
            elements.groupingBy { it.viewItemType }.eachCount().all { it.value == 1 }
        ) { "ViewHolder has to have single OffsetDrawer" }
        offsets.addAll(elements)
    }

    fun build(): MasterDecorator {
        return MasterDecorator(LayersBridge(underlay, overlay, offsets))
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

    var layerDrawer: LayerDrawer = emptyLayerDrawer()

    fun build(): Layer =
        Layer(viewItemType, layerDrawer)
}

class OffsetBuilder {

    var viewItemType: Int = UNDEFINE_VIEW_TYPE

    var offsetDrawer: OffsetDrawer = emptyOffsetDrawer()

    fun build(): Offset =
        Offset(viewItemType, offsetDrawer)
}