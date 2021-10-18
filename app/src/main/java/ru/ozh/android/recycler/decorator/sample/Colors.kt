package ru.ozh.android.recycler.decorator.sample

import android.graphics.Color
import ru.ozh.android.recycler.decorator.sample.Colors.Theme.DARK
import ru.ozh.android.recycler.decorator.sample.Colors.Theme.LIGHT

object Colors {
    var currentTheme = LIGHT

     val bgColor
        get() = when(currentTheme) {
            LIGHT -> Color.parseColor("#FFFFFF")
            DARK -> Color.parseColor("#535668")
        }
     val doneColor 
        get() = when(currentTheme) {
            LIGHT -> Color.parseColor("#535668")
            DARK -> Color.parseColor("#FFFFFF")
        }
     val processColor 
        get() = Color.parseColor("#20C286")

     val undoneTimeLineColor 
        get() = when(currentTheme) {
            LIGHT -> Color.parseColor("#CCCDD2")
            DARK -> Color.parseColor("#696D7C")
        }
     val undoneEventColor
        get() = when(currentTheme) {
            LIGHT -> Color.parseColor("#B8BAC1")
            DARK -> Color.parseColor("#9496A2")
        }
    
    enum class Theme {
        LIGHT,
        DARK
    }
}