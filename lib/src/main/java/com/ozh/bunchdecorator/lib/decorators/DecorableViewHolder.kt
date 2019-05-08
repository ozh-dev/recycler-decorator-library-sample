package com.ozh.bunchdecorator.lib.decorators
/**
 * Интерфейс для работы c [LinearItemsDecoration] и [RounderControllerDecorator]
 */
interface DecorableViewHolder {
    val itemDivider: Divider?
        get() = null

    val cornerRadius: Float
        get() = 0f
}