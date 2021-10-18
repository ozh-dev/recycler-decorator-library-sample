package ru.ozh.android.recycler.decorator.sample.chat.controller

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.ozh.android.recycler.decorator.sample.chat.ChatMessageDirection
import ru.ozh.android.recycler.decorator.sample.chat.controller.ChatMessageController.Holder
import ru.ozh.android.recycler.decorator.sample.databinding.ChatMessageLayoutBinding
import ru.ozh.android.recycler.decorator.sample.chat.ChatObject

class ChatMessageController : BindableItemController<ChatObject, Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder {
        return Holder(ChatMessageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemId(data: ChatObject): String {
        return "${data.messageDirection.name} ${data.id}"
    }

    class Holder(private val binding: ChatMessageLayoutBinding) :
        BindableViewHolder<ChatObject>(binding.root) {

        var chatMessageDirection: ChatMessageDirection? = null
            private set

        override fun bind(data: ChatObject) {
            this.chatMessageDirection = data.messageDirection
            val gravity = if (data.messageDirection == ChatMessageDirection.INCOME) {
                Gravity.END
            } else {
                Gravity.START
            }

            binding.chatVhRoot.gravity = gravity
            binding.messageAreaLayout.gravity = gravity
        }
    }
}