package com.ozh.bunchdecorator.lib.decorators

object Rules {
    const val MIDDLE = 1
    const val END = 2

    fun checkAllRule(rule: Int): Boolean {
        return rule and MIDDLE != 0
    }

    fun checkLastRule(rule: Int): Boolean {
        return rule and END != 0
    }
}