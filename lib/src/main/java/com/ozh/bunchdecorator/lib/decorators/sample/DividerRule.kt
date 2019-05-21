package com.ozh.bunchdecorator.lib.decorators.sample

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    Rules.MIDDLE,
    Rules.END
)
annotation class DividerRule