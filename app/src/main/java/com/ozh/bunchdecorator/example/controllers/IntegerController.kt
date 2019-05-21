package com.ozh.bunchdecorator.example.controllers

import android.view.ViewGroup
import com.ozh.bunchdecorator.example.R
import kotlinx.android.synthetic.main.item_controller.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class IntegerController : BindableItemController<Int, IntegerController.Holder>() {

    override fun getItemId(data: Int) =
        "$data"

    override fun createViewHolder(parent: ViewGroup): Holder =
        Holder(parent)

    class Holder(parent: ViewGroup) : BindableViewHolder<Int>(parent, R.layout.item_controller){

        override fun bind(data: Int) {
            itemView.text_view.text = "$data"
        }
    }
}