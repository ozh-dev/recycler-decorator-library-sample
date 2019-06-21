package com.ozh.bunchdecorator.example.controllers

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import kotlinx.android.synthetic.main.item_controller80.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class Controller(@LayoutRes val layoutRes: Int) : BindableItemController<String, Controller.Holder>() {

    override fun getItemId(data: String) = "$layoutRes:$data"

    override fun getItemHash(data: String): String = "$layoutRes:$data"

    override fun viewType(): Int {
        return layoutRes
    }

    override fun createViewHolder(parent: ViewGroup): Holder =
        Holder(parent, layoutRes)

    class Holder(parent: ViewGroup, layoutRes: Int) : BindableViewHolder<String>(parent, layoutRes) {

        override fun bind(data: String) {
            itemView.text_view.text = data
        }
    }
}