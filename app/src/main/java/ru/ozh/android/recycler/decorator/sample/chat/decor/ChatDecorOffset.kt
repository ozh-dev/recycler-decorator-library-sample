package ru.ozh.android.recycler.decorator.sample.chat.decor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.ozh.android.recycler.decorator.lib.Decorator
import ru.ozh.android.recycler.decorator.sample.chat.controller.ChatMessageController
import ru.ozh.android.recycler.decorator.sample.px

class ChatDecorOffset : Decorator.OffsetDecor {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            recyclerView: RecyclerView,
            state: RecyclerView.State
    ) {

        val vh = recyclerView.getChildViewHolder(view)
        if(vh !is ChatMessageController.Holder) {
            return
        }
        outRect.set(0, 8.px, 0, 0)
    }
}