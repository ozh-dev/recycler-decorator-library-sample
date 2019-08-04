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

/**
 * Common implementation for collect related objects
 */
class ScopeBuilder<T> {

    private val items: ArrayList<T> = arrayListOf()

    fun add(item: T): ScopeBuilder<T> {
        items.add(item)
        return this
    }

    fun build(): List<T> {
        return items
    }
}

