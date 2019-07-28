package com.ozh.dd.round

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

/**
 * [ViewOutlineProvider] для закругления View
 */
class RoundOutlineProvider(
        var outlineRadius: Float = 0f,
        var roundMode: RoundMode = RoundMode.NONE
) : ViewOutlineProvider() {

    private val topOffset
        get() = when (roundMode) {
            RoundMode.ALL, RoundMode.TOP -> 0
            RoundMode.NONE, RoundMode.BOTTOM -> cornerRadius.toInt()
        }
    private val bottomOffset
        get() = when (roundMode) {
            RoundMode.ALL, RoundMode.BOTTOM -> 0
            RoundMode.NONE, RoundMode.TOP -> cornerRadius.toInt()
        }
    private val cornerRadius
        get() = if (roundMode == RoundMode.NONE) {
            0f
        } else {
            outlineRadius
        }

    override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(
                0,
                0 - topOffset,
                view.width,
                view.height + bottomOffset,
                cornerRadius
        )
    }
}