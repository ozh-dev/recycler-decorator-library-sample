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

