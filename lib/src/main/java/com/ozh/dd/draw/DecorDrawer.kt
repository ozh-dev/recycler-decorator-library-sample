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