package ru.ozh.android.recycler.decorator.sample.timeline

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.ozh.android.recycler.decorator.sample.R
import ru.ozh.android.recycler.decorator.sample.Colors
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerEventBinding
import ru.ozh.android.recycler.decorator.sample.timeline.EventController.Holder
import ru.ozh.android.recycler.decorator.sample.timeline.Status.DONE
import ru.ozh.android.recycler.decorator.sample.timeline.Status.PROCESS
import ru.ozh.android.recycler.decorator.sample.timeline.Status.UNDEFINE
import ru.ozh.android.recycler.decorator.sample.timeline.Status.UNDONE
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class EventController : BindableItemController<Event, Holder>() {

    override fun getItemId(data: Event): String {
        return "${data.id}"
    }

    override fun createViewHolder(parent: ViewGroup): Holder {
        return Holder(ItemControllerEventBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class Holder(
        private val binding: ItemControllerEventBinding
        ) : BindableViewHolder<Event>(binding.root)  {

        var data: Event? = null
            private set

        override fun bind(data: Event) {
            this.data = data
            with(binding.eventTv) {
                text = data.eventName
                setTextColor(when(data.status) {
                    DONE -> Colors.doneColor
                    PROCESS -> Colors.processColor
                    UNDONE -> Colors.undoneEventColor
                    UNDEFINE -> Color.WHITE
                })
            }
        }

    }
}