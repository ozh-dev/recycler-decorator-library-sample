package com.ozh.bunchdecorator.lib.decorators.deprecated

/**
 * Интерфейс для работы c [LinearItemsDecoration] и [RounderControllerDecorator]
 */
interface DecorableViewHolder {
    val itemDivider: Gap?
        get() = null

    val cornerRadius: Float
        get() = 0f
}