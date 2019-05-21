package com.ozh.bunchdecorator.lib.decorators

import android.view.View
import androidx.recyclerview.widget.RecyclerView

object Utils {
    inline fun forEach(
        recyclerView: RecyclerView,
        action: (view: View, viewHolder: RecyclerView.ViewHolder, nextViewHolder: RecyclerView.ViewHolder?) -> Unit
    ) {
        with(recyclerView) {
            val startPosition = 0
            val finishPosition = childCount
            for (position in startPosition until finishPosition) {
                val view = getChildAt(position)
                val viewHolder = getChildViewHolder(view)
                val nextViewHolder = findViewHolderForAdapterPosition(viewHolder.adapterPosition + 1)
                action(view, viewHolder, nextViewHolder)
            }
        }
    }
}