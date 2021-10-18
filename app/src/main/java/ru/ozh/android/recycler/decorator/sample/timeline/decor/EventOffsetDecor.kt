package ru.ozh.android.recycler.decorator.sample.timeline.decor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.sample.timeline.EventController
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.px

class EventOffsetDecor : Decorator.OffsetDecor {

    private val verticalOffset = 32.px

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = recyclerView.width / 2
        outRect.top = verticalOffset
        outRect.left = offset
    }
}