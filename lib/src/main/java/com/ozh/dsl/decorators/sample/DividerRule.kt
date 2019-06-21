package com.ozh.dsl.decorators.sample

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    Rules.MIDDLE,
    Rules.END
)
annotation class DividerRule