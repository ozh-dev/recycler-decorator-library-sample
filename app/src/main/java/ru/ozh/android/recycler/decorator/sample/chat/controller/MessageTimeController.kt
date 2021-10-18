package ru.ozh.android.recycler.decorator.sample.chat.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.ozh.android.recycler.decorator.sample.chat.controller.MessageTimeController.Holder
import ru.ozh.android.recycler.decorator.sample.chat.decor.StiсkyHolder
import ru.ozh.android.recycler.decorator.sample.databinding.ItemControllerMessageTimeBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class MessageTimeController : BindableItemController<String, Holder>() {

    override fun getItemId(data: String): String {
        return data
    }

    override fun createViewHolder(parent: ViewGroup): Holder {
        return Holder(ItemControllerMessageTimeBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    class Holder(
        private val binding: ItemControllerMessageTimeBinding
    ) : BindableViewHolder<String>(binding.root), StiсkyHolder {

        override fun bind(data: String) {
            binding.timeTv.text = data
        }
    }
}