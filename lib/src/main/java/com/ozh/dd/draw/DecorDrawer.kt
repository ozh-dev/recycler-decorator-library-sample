package com.ozh.dd.draw

import com.ozh.dd.UNDEFINE_VIEW_TYPE

/**
 * Contain view type of [androidx.recyclerview.widget.RecyclerView.ViewHolder]
 * and drawer for draw decor or offset for ViewHolder
 */
class DecorDrawer<D>(val viewItemType: Int, val drawer: D) {

    class Builder<D> {

        private var viewItemType: Int = UNDEFINE_VIEW_TYPE
        private var drawer: D? = null

        fun attachTo(viewItemType: Int) = apply { this.viewItemType = viewItemType }

        fun drawBy(drawer: D) = apply { this.drawer = drawer }

        fun build(): DecorDrawer<D> {
            require(drawer != null) { "DecorDrawer class must have a drawer" }
            return DecorDrawer(viewItemType, drawer!!)

        }
    }
}