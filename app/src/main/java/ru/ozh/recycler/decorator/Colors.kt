package ru.ozh.recycler.decorator

import android.graphics.Color

object Colors {
    var currentTheme = Theme.LIGHT

     val bgColor
        get() = when(currentTheme) {
            Theme.LIGHT -> Color.parseColor("#FFFFFF")
            Theme.DARK -> Color.parseColor("#535668")
        }
     val doneColor 
        get() = when(currentTheme) {
            Theme.LIGHT -> Color.parseColor("#535668")
            Theme.DARK -> Color.parseColor("#FFFFFF")
        }
     val processColor 
        get() = Color.parseColor("#20C286")

     val undoneTimeLineColor 
        get() = when(currentTheme) {
            Theme.LIGHT -> Color.parseColor("#CCCDD2")
            Theme.DARK -> Color.parseColor("#696D7C")
        }
     val undoneEventColor
        get() = when(currentTheme) {
            Theme.LIGHT -> Color.parseColor("#B8BAC1")
            Theme.DARK -> Color.parseColor("#9496A2")
        }
    
    enum class Theme {
        LIGHT,
        DARK
    }
}