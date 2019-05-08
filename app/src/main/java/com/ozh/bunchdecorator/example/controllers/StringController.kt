package com.ozh.bunchdecorator.example.controllers

import android.view.ViewGroup
import com.ozh.bunchdecorator.example.R
import com.ozh.bunchdecorator.lib.decorators.DecorableViewHolder
import com.ozh.bunchdecorator.lib.decorators.Divider
import com.ozh.bunchdecorator.lib.decorators.Rules.MIDDLE
import kotlinx.android.synthetic.main.item_controller.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class StringController : BindableItemController<String, StringController.Holder>() {

    override fun getItemId(data: String) =
        data

    override fun createViewHolder(parent: ViewGroup): Holder =
        Holder(parent)

    class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.item_controller), DecorableViewHolder {

        override val itemDivider: Divider =
            Divider(
                itemView.context.resources.getColor(R.color.material_900),
                itemView.context.resources.getDimensionPixelOffset(R.dimen.dp28),
                itemView.context.resources.getDimensionPixelOffset(R.dimen.dp16),
                itemView.context.resources.getDimensionPixelOffset(R.dimen.dp16),
                MIDDLE
            )

        override fun bind(data: String) {
            itemView.text_view.text = data
        }
    }
}