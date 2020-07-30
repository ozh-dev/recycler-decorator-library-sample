package ru.ozh.recycler.decorator.timeline

import android.graphics.Color
import android.view.ViewGroup
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.item_controller_event.view.*
import ru.ozh.recycler.decorator.Colors
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class EventController : BindableItemController<Event, EventController.Holder>() {

    override fun getItemId(data: Event): String {
        return "${data.id}"
    }

    override fun createViewHolder(parent: ViewGroup): Holder {
        return Holder(parent)
    }

    class Holder(private val parent: ViewGroup) : BindableViewHolder<Event>(parent, R.layout.item_controller_event)  {

        var data: Event? = null
            private set

        override fun bind(data: Event) {
            this.data = data
            with(itemView.event_tv) {
                text = data.eventName
                setTextColor(when(data.status) {
                    Status.DONE -> Colors.doneColor
                    Status.PROCESS -> Colors.processColor
                    Status.UNDONE -> Colors.undoneEventColor
                    Status.UNDEFINE -> Color.WHITE
                })
            }
        }

    }
}