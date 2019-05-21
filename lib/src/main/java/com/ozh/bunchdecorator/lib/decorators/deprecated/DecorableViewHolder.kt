package com.ozh.bunchdecorator.lib.decorators.deprecated

import com.ozh.bunchdecorator.lib.decorators.sample.Gap

/**
 * Интерфейс для работы c [LinearItemsDecoration] и [RounderControllerDecorator]
 */
@Deprecated("Use com.ozh.bunchdecorator.lib.decorators.layers.Decorator")
interface DecorableViewHolder {
    val itemDivider: Gap?
        get() = null

    val cornerRadius: Float
        get() = 0f
}