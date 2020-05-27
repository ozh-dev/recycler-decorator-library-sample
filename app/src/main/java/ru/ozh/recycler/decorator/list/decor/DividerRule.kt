package ru.ozh.recycler.decorator.list.decor

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    Rules.MIDDLE,
    Rules.END
)
annotation class DividerRule