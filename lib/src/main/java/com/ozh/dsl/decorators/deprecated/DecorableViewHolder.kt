package com.ozh.dsl.decorators.deprecated

import com.ozh.dsl.decorators.sample.Gap

/**
 * Интерфейс для работы c [LinearItemsDecoration] и [RounderControllerDecorator]
 */
@Deprecated("Use com.ozh.dsl.decorators.layers.Decorator")
interface DecorableViewHolder {
    val itemDivider: Gap?
        get() = null

    val cornerRadius: Float
        get() = 0f
}