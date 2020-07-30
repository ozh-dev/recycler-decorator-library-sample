package ru.ozh.recycler.decorator.timeline.decor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.recycler.decorator.timeline.EventController
import ru.surfstudio.android.recycler.decorator.Decorator
import ru.ozh.recycler.decorator.px

class EventOffsetDecor : Decorator.OffsetDecor {

    private val verticalOffset = 32.px

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        val vh = recyclerView.getChildViewHolder(view) as EventController.Holder
        val offset = recyclerView.width / 2
        outRect.top = verticalOffset
        outRect.left = offset
    }
}