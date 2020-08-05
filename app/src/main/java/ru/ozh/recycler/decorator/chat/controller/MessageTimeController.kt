package ru.ozh.recycler.decorator.chat.controller

import android.view.ViewGroup
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.item_controller_message_time.view.*
import ru.ozh.recycler.decorator.chat.decor.StiсkyHolder
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class MessageTimeController : BindableItemController<String, MessageTimeController.Holder>() {

    override fun getItemId(data: String): String {
        return data
    }

    override fun createViewHolder(parent: ViewGroup): Holder {
        return Holder(parent)
    }

    class Holder(
        parent: ViewGroup
    ) : BindableViewHolder<String>(parent, R.layout.item_controller_message_time), StiсkyHolder {
        override fun bind(data: String) {
            itemView.time_tv.text = data
        }
    }
}